package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Factuur;
import com.example.eindopdracht_backend_ipmroved.entity.Product;
import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.repository.ProductRepository;
import com.example.eindopdracht_backend_ipmroved.service.ProductService;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;
    private Factuur factuur1;
    private Factuur factuur2;
    private Klant klant1;
    private Klant klant2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Maak Klant objecten
        klant1 = new Klant(10L, "Anne", "Slager", "slager@example.com", "0654321098", "Havenstraat 3", "Utrecht", "3456 CD", false, new ArrayList<>());
        klant2 = new Klant(11L, "Piet", "Molensteen", "molensteen@example.com", "0687654321", "Molenstraat 2", "Rotterdam", "2345 BC", false, new ArrayList<>());

        // Maak Factuur objecten
        factuur1 = new Factuur(9L, LocalDate.of(2024, 7, 10), klant1, new ArrayList<>());
        factuur2 = new Factuur(10L, LocalDate.of(2024, 7, 11), klant2, new ArrayList<>());

        // Maak Product objecten
        product1 = new Product(6L, "Fietsketting", 29.99, factuur1);
        product2 = new Product(7L, "Fietsspaken", 19.99, factuur2);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products).containsExactlyInAnyOrder(product1, product2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById_existingId() {
        when(productRepository.findById(6L)).thenReturn(Optional.of(product1));

        Optional<Product> foundProduct = Optional.ofNullable(productService.getProductById(6L));

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getNaam()).isEqualTo("Fietsketting");
        assertThat(foundProduct.get().getPrijs()).isEqualTo(29.99);
        assertThat(foundProduct.get().getFactuur()).isEqualTo(factuur1);
        verify(productRepository, times(1)).findById(6L);
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        Product savedProduct = productService.createProduct(product1);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getNaam()).isEqualTo("Fietsketting");
        assertThat(savedProduct.getPrijs()).isEqualTo(29.99);
        assertThat(savedProduct.getFactuur()).isEqualTo(factuur1);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testUpdateProduct_nonExistingId() {
        when(productRepository.findById(7L)).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> productService.updateProduct(7L, product2));

        assertThat(thrown).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Product with id 7 not found");

        verify(productRepository, never()).save(any(Product.class));
    }
}
