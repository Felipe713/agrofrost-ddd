# Checklist Hito 3

## Pilar 1 — Arquitectura Limpia

- [x] domain presente
- [x] application presente
- [x] infrastructure presente
- [x] dependencia hacia adentro
- [x] cero frameworks en domain
- [x] cero frameworks en application

## Pilar 2 — DDD

- [x] Entity con identidad única
- [x] Aggregate Root identificada
- [x] Value Objects con record
- [x] Value Objects auto-validantes
- [x] Ubiquitous Language documentado
- [x] Bounded Context documentado
- [x] comportamiento de negocio encapsulado

## Pilar 3 — Repository Pattern

- [x] FieldRepository puro
- [x] InMemoryFieldRepository en infrastructure
- [x] casos de uso cohesionados
- [x] constructor injection
- [x] cero `new InMemoryFieldRepository()` dentro de application
- [x] tests con mocks

## Validaciones manuales

Ejecutadas antes del commit: `mvn clean compile`, `mvn test`, `mvn clean verify`, `git diff --check` y las búsquedas `rg` de imports y construcciones prohibidas. También se revisan los árboles con `find` y el reporte en `target/site/jacoco/index.html`.

## Evidencias adicionales de calidad

- [x] Field implementa igualdad por identidad mediante FieldId.
- [x] Tests validan Entity equality.
- [x] ArchitectureRulesTest protege la dirección de dependencias.
- [x] CI ejecuta `mvn clean verify` en GitHub Actions.
- [x] README documenta correctamente las dependencias arquitectónicas.
- [x] Context Map documenta las fronteras del Frost Monitoring Context.
