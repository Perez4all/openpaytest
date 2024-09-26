package org.openpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarvelQueriesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer statusCode;

    @Column
    private String requestType;

    @Column
    private LocalDateTime requestDateTime;

    @Column
    private LocalDateTime responseDateTime;

    @Column
    private String hash;

    @Column
    private String info;

    @Column
    private String url;

    @Column
    private Long responseTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private byte[] result;
}
