package jbosswildfly.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Vehicle implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String make;

   @Column
   private String model;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Vehicle))
      {
         return false;
      }
      Vehicle other = (Vehicle) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getMake()
   {
      return make;
   }

   public void setMake(String make)
   {
      this.make = make;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (make != null && !make.trim().isEmpty())
         result += "make: " + make;
      if (model != null && !model.trim().isEmpty())
         result += ", model: " + model;
      return result;
   }
}