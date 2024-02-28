package com.ventasx.SistemaVentas;

import com.ventasx.SistemaVentas.Configuration.Security.Auth.AuthenticationRequest;
import com.ventasx.SistemaVentas.Configuration.Security.Auth.AuthenticationService;
import com.ventasx.SistemaVentas.Persistence.Entity.*;
import com.ventasx.SistemaVentas.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SistemaVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVentasApplication.class, args);
	}

	//Creation of users only for tests, that is because is "create-drop" configured the project.
	@Bean
	public CommandLineRunner commandLineRunner(IUserService iUserService, AuthenticationService authenticationService,
											   IMenuService iMenuService, ISubMenuService iSubMenuService,
											   IGroupProductService iGroupProductService, ICategoryProductService iCategoryProductService,
											   ITypeProductService iTypeProductService, IBrandProductService iBrandProductService,
											   IMeasureUnitService iMeasureUnitService, IProductService iProductService){
		return args -> {
			//Users Creation
			var admin = User.builder()
					.name("Admin")
					.lastName("Admin Last Name")
					.username("admin")
					.password("12345")
					.isEnabled(true)
					.userCreatorId(1L)
					.role(Role.ADMIN)
					.build();
			User userAdmin = iUserService.createUserPasswordEncoder(admin);
//			var adminAuthenticationRequest = AuthenticationRequest.builder()
//					.username(admin.getUsername())
//					.password("12345")
//					.build();
			System.out.println("User " + userAdmin.getName() + " created: " + admin.getUsername() + " - 12345");
//			System.out.println("Admin Token: " + authenticationService.authenticate(adminAuthenticationRequest).getAccessToken());

			var manager = User.builder()
					.name("Manager")
					.lastName("Manager Last Name")
					.username("manager")
					.password("12345")
					.isEnabled(true)
					.userCreatorId(1L)
					.role(Role.MANAGER)
					.build();
			User userManager = iUserService.createUserPasswordEncoder(manager);
//			var managerAuthenticationRequest = AuthenticationRequest.builder()
//					.username(userManager.getUsername())
//					.password("12345")
//					.build();
			System.out.println("User " + userManager.getName() + " created: " + manager.getUsername() + " - 12345");
//			System.out.println("Manager Token: " + authenticationService.authenticate(managerAuthenticationRequest).getAccessToken());

			//Menu Creation
			var maintenanceMenu = Menu.builder()
					.id(1)
					.name("Mantenimiento")
					.icon("pi-wrench")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.build();
			Menu maintenanceMenuCreated = iMenuService.create(maintenanceMenu);
			System.out.printf("Menu maintenance created: %d - %s - %s\n",maintenanceMenuCreated.getId(), maintenanceMenuCreated.getName(),
					maintenanceMenuCreated.getIcon());

			var securityMenu = Menu.builder()
					.id(2)
					.name("Seguridad")
					.icon("pi-lock")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.build();
			Menu securityMenuCreated = iMenuService.create(securityMenu);
			System.out.printf("Menu security created: %d - %s - %s\n",securityMenuCreated.getId(), securityMenuCreated.getName(),
					securityMenuCreated.getIcon());

			//SubMenu Creation
			var productSubmenu = SubMenu.builder()
					.id(1)
					.name("Producto")
					.icon("pi-box")
					.url("/pages/products")
					.menu(maintenanceMenuCreated)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.build();
			SubMenu productMenuCreated = iSubMenuService.create(productSubmenu);
			System.out.printf("SubMenu product created: %d - %s - %s - %s - %s\n",productMenuCreated.getId(), productMenuCreated.getName(),
					productMenuCreated.getIcon(), productMenuCreated.getUrl(), productMenuCreated.getMenu().getName());

			var measureUnitSubmenu = SubMenu.builder()
					.id(2)
					.name("Unidad Medida")
					.icon("pi-filter")
					.url("/pages/measure-units")
					.menu(maintenanceMenuCreated)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.build();
			SubMenu measureUnitMenuCreated = iSubMenuService.create(measureUnitSubmenu);
			System.out.printf("SubMenu product created: %d - %s - %s - %s - %s\n",measureUnitMenuCreated.getId(), measureUnitMenuCreated.getName(),
					measureUnitMenuCreated.getIcon(), measureUnitMenuCreated.getUrl(), measureUnitMenuCreated.getMenu().getName());

			var usersSubmenu = SubMenu.builder()
					.id(3)
					.name("Usuarios")
					.icon("pi-users")
					.url("/pages/users")
					.menu(securityMenuCreated)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.build();
			SubMenu usersSubmenuCreated = iSubMenuService.create(usersSubmenu);
			System.out.printf("SubMenu product created: %d - %s - %s - %s - %s\n",usersSubmenuCreated.getId(), usersSubmenuCreated.getName(),
					usersSubmenuCreated.getIcon(), usersSubmenuCreated.getUrl(), usersSubmenuCreated.getMenu().getName());

			//Relation SubMenu-User Creation
			if (iUserService.insertSubmenuUser(1L,1) == 1){ System.out.println("Usuario - Submenu: admin - producto"); } else {System.out.println("Relacion no creada.");}
			if (iUserService.insertSubmenuUser(1L,2) == 1){ System.out.println("Usuario - Submenu: admin - unidad medida"); } else {System.out.println("Relacion no creada.");}
			if (iUserService.insertSubmenuUser(1L,3) == 1){ System.out.println("Usuario - Submenu: admin - usuarios"); } else {System.out.println("Relacion no creada.");}
			if (iUserService.insertSubmenuUser(2L,1) == 1){ System.out.println("Usuario - Submenu: manager - producto"); } else {System.out.println("Relacion no creada.");}
			if (iUserService.insertSubmenuUser(2L,2) == 1){ System.out.println("Usuario - Submenu: manager - unidad medida"); } else {System.out.println("Relacion no creada.");}

			//Group - Category - Type - Brand Product Creation
			BrandProduct genericBrandProduct = BrandProduct.builder()
					.id(1L)
					.name("Genérico")
					.description("Genérico")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iBrandProductService.create(genericBrandProduct);
			System.out.println("Marca Genérico creada.");

			GroupProduct genericGroupProduct = GroupProduct.builder()
					.id(1L)
					.name("Genérico")
					.description("Genérico")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iGroupProductService.create(genericGroupProduct);
			System.out.println("Grupo Genérico creada.");

			CategoryProduct genericCategoryProduct = CategoryProduct.builder()
					.id(1L)
					.name("Genérico")
					.description("Genérico")
					.groupProduct(genericGroupProduct)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iCategoryProductService.create(genericCategoryProduct);
			System.out.println("Categoria Genérico creada.");

			TypeProduct genericTypeProduct = TypeProduct.builder()
					.id(1L)
					.name("Genérico")
					.description("Genérico")
					.categoryProduct(genericCategoryProduct)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(genericTypeProduct);
			System.out.println("Tipo Genérico creada.");

			MeasureUnit unMeasureUnit = MeasureUnit.builder()
					.id(1)
					.name("UN")
					.description("Unidad")
					.userCreatorId(1L)
					.creationDate(LocalDateTime.now())
					.isEnabled(true)
					.build();
			iMeasureUnitService.create(unMeasureUnit);
			System.out.println("Unidad de Medida UN creada.");

			Product testProduct = Product.builder()
					.id(1L)
					.brandProduct(genericBrandProduct)
					.groupProduct(genericGroupProduct)
					.categoryProduct(genericCategoryProduct)
					.typeProduct(genericTypeProduct)
					.code("123456789")
					.measureUnit(unMeasureUnit)
					.name("Producto Prueba")
					.description("Producto Prueba")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.minimumStock(2F)
					.sellingPriceSoles(20.4F)
					.sunatType("BIEN")
					.build();
			iProductService.create(testProduct);
			System.out.println("Producto PRUEBA creada.");

//-------------------------------------para probar el cascade select----------------------------------------------------------------
			GroupProduct group1 = GroupProduct.builder()
					.id(2L)
					.name("Grupo1")
					.description("Grupo1")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iGroupProductService.create(group1);
			System.out.println("Grupo1 creada.");

			GroupProduct group2 = GroupProduct.builder()
					.id(3L)
					.name("Grupo2")
					.description("Grupo2")
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iGroupProductService.create(group2);
			System.out.println("Grupo2 creada.");

			CategoryProduct category1 = CategoryProduct.builder()
					.id(2L)
					.name("Categoria 1")
					.description("Categoria 1")
					.groupProduct(group1)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iCategoryProductService.create(category1);
			System.out.println("Categoria 1 creada.");

			CategoryProduct category2 = CategoryProduct.builder()
					.id(3L)
					.name("Categoria 2")
					.description("Categoria 2")
					.groupProduct(group1)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iCategoryProductService.create(category2);
			System.out.println("Categoria 2 creada.");

			CategoryProduct category3 = CategoryProduct.builder()
					.id(4L)
					.name("Categoria 3")
					.description("Categoria 3")
					.groupProduct(group2)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iCategoryProductService.create(category3);
			System.out.println("Categoria 3 creada.");

			CategoryProduct category4 = CategoryProduct.builder()
					.id(5L)
					.name("Categoria 4")
					.description("Categoria 4")
					.groupProduct(group2)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iCategoryProductService.create(category4);
			System.out.println("Categoria 4 creada.");

			TypeProduct type1 = TypeProduct.builder()
					.id(2L)
					.name("Tipo1")
					.description("Tipo1")
					.categoryProduct(category1)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type1);
			System.out.println("Tipo 1 creada.");

			TypeProduct type2 = TypeProduct.builder()
					.id(3L)
					.name("Tipo2")
					.description("Tipo2")
					.categoryProduct(category3)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type2);
			System.out.println("Tipo 2 creada.");

			TypeProduct type3 = TypeProduct.builder()
					.id(4L)
					.name("Tipo3")
					.description("Tipo3")
					.categoryProduct(category2)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type3);
			System.out.println("Tipo 3 creada.");

			TypeProduct type4 = TypeProduct.builder()
					.id(5L)
					.name("Tipo4")
					.description("Tipo4")
					.categoryProduct(category4)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type4);
			System.out.println("Tipo 4 creada.");

			TypeProduct type5 = TypeProduct.builder()
					.id(6L)
					.name("Tipo5")
					.description("Tipo5")
					.categoryProduct(category1)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type5);
			System.out.println("Tipo 5 creada.");

			TypeProduct type6 = TypeProduct.builder()
					.id(7L)
					.name("Tipo6")
					.description("Tipo6")
					.categoryProduct(category1)
					.creationDate(LocalDateTime.now())
					.userCreatorId(1L)
					.isEnabled(true)
					.build();
			iTypeProductService.create(type6);
			System.out.println("Tipo 6 creada.");
//-------------------------------------para probar el cascade select----------------------------------------------------------------

		};
	}
}
