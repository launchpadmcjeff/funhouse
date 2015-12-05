package jbosswildfly.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jbosswildfly.model.Vehicle;

/**
 * Backing bean for Vehicle entities.
 * <p/>
 * This class provides CRUD functionality for all Vehicle entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class VehicleBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Vehicle entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Vehicle vehicle;

   public Vehicle getVehicle()
   {
      return this.vehicle;
   }

   public void setVehicle(Vehicle vehicle)
   {
      this.vehicle = vehicle;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(unitName = "jbosswildfly-persistence-unit", type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
         this.conversation.setTimeout(1800000L);
      }

      if (this.id == null)
      {
         this.vehicle = this.example;
      }
      else
      {
         this.vehicle = findById(getId());
      }
   }

   public Vehicle findById(Long id)
   {

      return this.entityManager.find(Vehicle.class, id);
   }

   /*
    * Support updating and deleting Vehicle entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.vehicle);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.vehicle);
            return "view?faces-redirect=true&id=" + this.vehicle.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Vehicle deletableEntity = findById(getId());

         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Vehicle entities with pagination
    */

   private int page;
   private long count;
   private List<Vehicle> pageItems;

   private Vehicle example = new Vehicle();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Vehicle getExample()
   {
      return this.example;
   }

   public void setExample(Vehicle example)
   {
      this.example = example;
   }

   public String search()
   {
      this.page = 0;
      return null;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Vehicle> root = countCriteria.from(Vehicle.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Vehicle> criteria = builder.createQuery(Vehicle.class);
      root = criteria.from(Vehicle.class);
      TypedQuery<Vehicle> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Vehicle> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String make = this.example.getMake();
      if (make != null && !"".equals(make))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("make")), '%' + make.toLowerCase() + '%'));
      }
      String model = this.example.getModel();
      if (model != null && !"".equals(model))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("model")), '%' + model.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Vehicle> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Vehicle entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Vehicle> getAll()
   {

      CriteriaQuery<Vehicle> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Vehicle.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Vehicle.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final VehicleBean ejbProxy = this.sessionContext.getBusinessObject(VehicleBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Vehicle) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Vehicle add = new Vehicle();

   public Vehicle getAdd()
   {
      return this.add;
   }

   public Vehicle getAdded()
   {
      Vehicle added = this.add;
      this.add = new Vehicle();
      return added;
   }
}
