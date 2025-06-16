package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT o.* FROM ogrenci o \n" +
            "INNER JOIN islem i ON o.ogrno = i.ogrno;";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT o.* FROM ogrenci o LEFT JOIN islem i ON i.ogrno = o.ogrno WHERE i.ogrno IS NULL;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT \n" +
            "  o.sinif,\n" +
            "  COUNT(i.kitapno) AS count\n" +
            "FROM ogrenci o\n" +
            "INNER JOIN islem i ON o.ogrno = i.ogrno\n" +
            "WHERE o.sinif IN ('10A', '10B')\n" +
            "GROUP BY o.sinif\n" +
            "ORDER BY o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(ogrno) FROM ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) AS farkli_isim_sayisi\n" +
            "FROM ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT o.ad , COUNT(o.ad) FROM ogrenci o\n" +
            "GROUP BY o.ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "    SELECT sinif, COUNT(*) AS count\n" +
            "    FROM ogrenci\n" +
            "    GROUP BY sinif\n" +
            "    ORDER BY sinif DESC";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT DISTINCT o.* FROM ogrenci o INNER JOIN islem i ON i.ogrno = o.ogrno;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
