# 📝 Todo App API

Un microserviciu Spring Boot pentru gestionarea task-urilor (to-do), securizat cu JWT. Acesta face parte dintr-o arhitectură bazată pe microservicii unde autentificarea este gestionată de un **auth-server** separat.

---

## 📦 Arhitectură
```text
[ React Frontend ]
        |
        | --> login / register / Google OAuth
        ↓
[ Auth Server ] -- emite JWT (access + refresh)
        |
        ↓ (Authorization: Bearer <JWT>)
[ Todo App API ] -- validează JWT + gestionează TODO-urile
```

---

## 🚀 Funcționalități

- 🔐 JWT-based Authentication (token validat local, fără sesiuni)
- 📅 CRUD complet pentru TODO-uri
- 📆 Filtrare TODO-uri după dată
- 🧾 DTO-uri curate (separate de entități)
- 🛡️ Stateless security cu `Spring Security`

---

~~## ⚙️ Configurație~~

### 🔑 JWT

Asigură-te că folosești aceeași cheie JWT ca și în `auth-server`.

```yaml
# application.yml
jwt:
  secret: your-secret-jwt-key
```