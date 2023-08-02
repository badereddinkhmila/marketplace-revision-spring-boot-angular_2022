package com.ecommerce.marketplace.Entity;

import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_image_files", uniqueConstraints = {@UniqueConstraint(columnNames = "fileName")})
public class ProductImageFile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String fileName;

    @NonNull
    @Lob
    private byte[] data;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public String getImageUri() {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(this.id))
                .toUriString();
        return fileDownloadUri;
    }

}
