# Lenguaje ubicuo — Frost Monitoring Context

Este contexto cubre el monitoreo y la evaluación de heladas de un campo. No incluye APIs meteorológicas concretas, bases de datos, mensajería real, sensores, interfaz web, frontend TypeScript, Spring Boot ni controladores REST: son adaptadores externos futuros.

| Término | Significado |
| --- | --- |
| Field | Campo agrícola monitoreado por AgroFrost. |
| FieldId | Identificador único y estable del campo. |
| FieldName | Nombre legible del campo. |
| Crop | Cultivo establecido en el campo. |
| Critical Temperature | Temperatura definida para el cultivo/campo desde la cual el riesgo es crítico. |
| Measured Temperature | Temperatura observada actualmente para el campo. |
| Frost Assessment | Resultado de comparar temperatura medida y crítica. |
| Frost Risk Level | Clasificación final SAFE, WARNING o CRITICAL. |
| Warning Margin | Margen de precaución de 2.0 °C sobre la temperatura crítica. |
| Frost Monitoring | Proceso de obtener la temperatura actual y evaluar el riesgo del campo. |
| Critical Frost Alert | Notificación conceptual emitida cuando el Frost Assessment es CRITICAL. |

El código conserva estos nombres en inglés para evitar sinónimos ambiguos como Plot, Parcel o RiskCheck.
