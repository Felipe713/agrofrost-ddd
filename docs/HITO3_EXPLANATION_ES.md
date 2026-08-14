# Explicación del Hito 3

## Arquitectura y DDD

Clean Architecture evita que decisiones externas, como una base de datos o framework, contaminen las reglas que dan valor al negocio. **Domain** contiene el modelo y las reglas puras; **Application** orquesta los casos de uso; **Infrastructure** contiene adaptadores reemplazables. La regla es hacia adentro: infrastructure depende de application y domain; application depende de domain; domain no conoce capas externas.

DDD organiza el software alrededor del lenguaje del negocio. El **Ubiquitous Language** es el vocabulario compartido documentado en `UBIQUITOUS_LANGUAGE_ES.md`. El **Bounded Context** es Frost Monitoring Context: registro, temperatura y riesgo de helada; APIs, PostgreSQL, mensajes reales y UI quedan fuera.

Una **Entity** tiene identidad: `Field` sigue siendo el mismo campo por su `FieldId`. Un **Value Object** vale por sus datos: `CriticalTemperature`, `MeasuredTemperature`, `Crop`, `FieldName`, `FieldId` y `FrostAssessment`. `Field` es el **Aggregate Root** porque protege la evaluación y concentra los cambios del agregado. Los records ayudan porque son inmutables, comparables por valor y no tienen setters.

La igualdad de una Entity se define por identidad. Dos objetos `Field` con el mismo `FieldId` representan la misma entidad aunque cambien nombre, cultivo u otros atributos. En cambio, los Value Objects se comparan por valor: dos records equivalentes contienen los mismos datos.

## Repositorio e inversión de dependencias

`FieldRepository` describe cómo guardar y obtener `Field` sin SQL ni tecnología; está en domain porque el dominio necesita ese contrato. `InMemoryFieldRepository` está en infrastructure porque es un detalle técnico. En el futuro PostgreSQL puede implementar la misma interfaz sin modificar Domain.

La inversión de dependencias significa que los casos de uso dependen de interfaces internas, no de detalles. La **constructor injection** recibe esas interfaces al crear el objeto, deja dependencias explícitas y permite mocks. Por eso los use cases no usan `new InMemoryFieldRepository()`.

`RegisterFieldUseCase` valida el campo, busca su `FieldId`, rechaza duplicados y guarda. `MonitorFieldUseCase` busca el campo, solicita `MeasuredTemperature`, delega en `Field.assessFrost`, alerta solo si es CRITICAL y retorna el assessment. La regla está en un único lugar: `<= critical` es CRITICAL; `<= critical + 2.0` es WARNING; el resto SAFE.

## Relación con Hito 1

Hito 3 evoluciona conceptualmente desde Hito 1: conserva reglas y validaciones TDD, pero separa responsabilidades con DDD y Clean Architecture. Son repositorios independientes; no hay dependencia Maven ni de runtime.

## Preguntas que podrían hacerme en la defensa

**¿Por qué no Spring?** Para hacer visible el diseño puro antes de incorporar infraestructura.

**¿Por qué `Field` no es un record?** Tiene identidad y comportamiento de agregado, no solo datos por valor.

**¿Por qué no hay SQL?** El repositorio en memoria demuestra que la persistencia es intercambiable.

**¿Dónde vive la regla de riesgo?** En `Field.assessFrost`, no en el caso de uso.

**¿Por qué `Crop` no es enum?** El negocio puede registrar nuevos cultivos sin recompilar el dominio.

**¿Por qué dos objetos `Field` con el mismo `FieldId` son la misma Entity?** Porque una Entity en DDD se define por una identidad estable que permanece durante su ciclo de vida. Sus atributos pueden cambiar sin que deje de representar el mismo objeto de negocio.
