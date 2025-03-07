package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "public.announcements_theme")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Public.announcementsTheme {

    @Column(name = ""type"", length = 255, nullable = false)
    private String "type";

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "el_desc", length = 255)
    private String elDesc;

    @Column(name = "tr_desc", length = 255)
    private String trDesc;

    @Column(name = "en_desc", length = 255)
    private String enDesc;

    @Column(name = "hr_desc", length = 255)
    private String hrDesc;

}
