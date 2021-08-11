package org.acme.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Uni;
import org.acme.model.Organization;

@ApplicationScoped
public class OrganizationRepository {
    
    public List<Organization> organizationSet = new ArrayList<Organization>();

    public List<Organization> getOrganizationSet() {
      return this.organizationSet;
    }

    public void setOrganizationSet(List<Organization> organizationSet) {
      this.organizationSet = organizationSet;
    }


    public OrganizationRepository () {
      // default constructor 
    }

    public Organization add (Organization organization) {
        organizationSet.add(organization);
        return organization;
    }

    public Uni<Organization> findById(Long id) {
      Optional<Organization> organization = organizationSet.stream().filter(a -> a.getId().equals(id)).findFirst();
      if (organization.isPresent())
        return Uni.createFrom().item(organization.get());
      else
        return null;
	  }
}
