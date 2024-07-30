package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Factuur;
import com.example.eindopdracht_backend_ipmroved.entity.Product;
import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.repository.ProductRepository;
import com.example.eindopdracht_backend_ipmroved.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        klant1 = new Klant(3L, "Anne", "Slager", "Havenstraat 3", "slager@example.com", "3456 CD", "0654321098", "Utrecht");
        klant2 = new Klant(4L, "Piet", "Molensteen", "Molenstraat 2", "molensteen@example.com", "2345 BC", "0687654321", "Rotterdam");

        // Maak Factuur objecten
        factuur1 = new Factuur(7L, LocalDate.of(2024, 7, 10), klant1, Collections.emptyList());
        factuur2 = new Factuur(8L, LocalDate.of(2024, 7, 11), klant2, Collections.emptyList());

        // Maak Product objecten
        product1 = new Product(1L, "Fietsketting", 29.99, factuur1);
        product2 = new Product(2L, "Fietsspaken", 19.99, factuur2);
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
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getNaam()).isEqualTo("Fietsketting");
        assertThat(foundProduct.get().getPrijs()).isEqualTo(29.99);
        assertThat(foundProduct.get().getFactuur()).isEqualTo(factuur1);
        verify(productRepository, times(1)).findById(1L);
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
    public void testUpdateProduct_existingId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        Product updatedProduct = productService.updateProduct(1L, product1);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getNaam()).isEqualTo("Fietsketting");
        assertThat(updatedProduct.getPrijs()).isEqualTo(29.99);
        assertThat(updatedProduct.getFactuur()).isEqualTo(factuur1);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testUpdateProduct_nonExistingId() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Product updatedProduct = productService.updateProduct(2L, product2);

        assertThat(updatedProduct).isNull();
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
