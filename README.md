
# TimeTracker

> Basit, kendi kendine barındırılabilen bir **zaman takip ve proje yönetimi** REST API’si. Kullanıcılar projeler oluşturur, çalışma oturumlarını (WorkLog) kaydeder ve dönemsel raporlar (PDF / Excel) alır. **Spring Boot 3** tabanlıdır ve güvenlik katmanı opsiyoneldir; odak Backend geliştirme yetkinliklerini sergilemektir.

---

## Özellikler

- **Proje ve Çalışma Kaydı (CRUD)** – Kullanıcı bazlı proje oluşturma, WorkLog ekleme/düzenleme.
- **Zaman Çakışma Kontrolü** – Aynı zaman dilimine denk gelen WorkLog engellenir.
- **Raporlama** – Belirtilen tarih aralığı için PDF veya Excel çıktı.
- **Soft‑delete** – Kayıtlar kalıcı silinmeden ‘archived’ olarak işaretlenir.
- **RESTful API v1** – Swagger/OpenAPI dokümantasyonu otomatik üretilir.
- **Docker‑friendly** – Uygulama ve PostgreSQL tek komutla ayağa kalkar.

---

## Kullanılan Teknolojiler

| Katman        | Teknoloji                      |
| ------------- | ------------------------------ |
| Dil           | Java 21                        |
| Çatı          | Spring Boot 3.x                |
| ORM           | Spring Data JPA (Hibernate 6)  |
| DB            | PostgreSQL 15 (dev: H2)        |
| Migrasyon     | Flyway                         |
| Haritalama    | MapStruct                      |
| Yardımcı      | Lombok, Logback                |
| Dokümantasyon | springdoc‑openapi              |
| CI/CD         | GitHub Actions, Docker Compose |

---

## Kurulum

### Ön Koşullar

- **JDK 21** ve **Maven 3.9+**
- İsteğe bağlı: **Docker 24+** / **Docker Compose**

### Hızlı Başlangıç (Docker)

```bash
git clone https://github.com/kullanici/timetracker.git
cd timetracker
docker compose up -d
```

Uygulama `http://localhost:8080` adresinde, Swagger UI ise `http://localhost:8080/swagger-ui.html` altında yayında olur.

### Yerel Çalıştırma (dev profili)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

H2 konsolu `http://localhost:8080/h2-console` → JDBC URL: `jdbc:h2:mem:timetracker`.

---

## Yapılandırma Dosyaları

- `src/main/resources/application-dev.properties` → H2, `spring.jpa.hibernate.ddl-auto=update`
- `src/main/resources/application-prod.properties` → PostgreSQL, Flyway migration’ları

DB bilgileriniz farklıysa ilgili profile’daki `spring.datasource.*` alanlarını düzenleyin.

---

## API Önizlemesi (v1)

| HTTP | Endpoint                                       | Açıklama                   |
| ---- | ---------------------------------------------- | -------------------------- |
| GET  | `/api/v1/projects`                             | Projeleri sayfalı listeler |
| POST | `/api/v1/projects`                             | Yeni proje oluştur         |
| GET  | `/api/v1/projects/{id}`                        | Proje detayı               |
| POST | `/api/v1/worklogs`                             | WorkLog ekle               |
| GET  | `/api/v1/worklogs?projectId=&from=&to=`        | Filtreli WorkLog listesi   |
| GET  | `/api/v1/reports?userId=&from=&to=&format=pdf|xlsx` | Rapor indir          |

Tam liste ve model şemaları için Swagger UI’yi ziyaret edin.

---

## Yol Haritası

- [x] Entity & CRUD katmanı
- [x] PDF / Excel raporları
- [ ] Çakışma kontrol iş kuralı
- [ ] Unit & Integration test (≥ %80 coverage)
- [ ] Basit React panosu (v2)
- [ ] Spring Security + JWT (v3, opsiyonel)

---

## Katkıda Bulunma

1. Repoyu **fork**’layın.
2. `feature/` önekiyle yeni bir **branch** oluşturun.
3. Değişikliklerinizi **commit** edin ve **pull request** açın.
4. PR açıklamasında yapılan değişiklikleri ve ilgili issue’ları belirtin.

> 💡 **Kod Stili**: Google Java Style + Spotless (mvn spotless:apply).

---

## Lisans

Bu proje **MIT Lisansı** ile lisanslanmıştır. Ayrıntılar için `LICENSE` dosyasına bakın.
