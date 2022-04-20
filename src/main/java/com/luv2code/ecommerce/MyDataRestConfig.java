package com.luv2code.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	@Autowired
	EntityManager entityManager;
	
	

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions= {HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE};
		
		
		config.getExposureConfiguration().forDomainType(Product.class)
		.withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions));
		

		config.getExposureConfiguration().forDomainType(ProductCategory.class)
		.withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions));
		
		exposeIds(config);
		
	}



	private void exposeIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities=entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType<?> type :entities) {
			entityClasses.add(type.getJavaType());
		}
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
		
	}

}
