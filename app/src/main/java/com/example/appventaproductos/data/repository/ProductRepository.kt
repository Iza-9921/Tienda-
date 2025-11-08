package com.example.appventaproductos.data.repository

import com.example.appventaproductos.R
import com.example.appventaproductos.data.local.dao.AccesorioDao
import com.example.appventaproductos.data.local.dao.CarriolaDao
import com.example.appventaproductos.data.local.dao.RopaDao
import com.example.appventaproductos.data.local.entity.AccesorioEntity
import com.example.appventaproductos.data.local.entity.CarriolaEntity
import com.example.appventaproductos.data.local.entity.RopaEntity
import com.example.appventaproductos.data.model.Accesorios
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Ropa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(
    private val ropaDao: RopaDao,
    private val carriolaDao: CarriolaDao,
    private val accesorioDao: AccesorioDao
) {

    fun getRopa(): Flow<List<Ropa>> =
        ropaDao.getAll().map { list -> list.map { it.toDomain() } }

    fun getRopaById(id: Int): Flow<Ropa?> =
        ropaDao.getById(id).map { it?.toDomain() }

    suspend fun insertRopa(item: Ropa) {
        ropaDao.insert(item.toEntity())
    }

    suspend fun updateRopa(item: Ropa) {
        ropaDao.update(item.toEntity())
    }

    suspend fun deleteRopa(id: Int) {
        ropaDao.deleteById(id)
    }

    suspend fun seedRopaIfNeeded() {
        if (ropaDao.count() == 0) {
            ropaDao.insertAll(defaultRopa())
        }
    }

    fun getCarriolas(): Flow<List<Carriola>> =
        carriolaDao.getAll().map { list -> list.map { it.toDomain() } }

    fun getCarriolaById(id: Int): Flow<Carriola?> =
        carriolaDao.getById(id).map { it?.toDomain() }

    suspend fun insertCarriola(item: Carriola) {
        carriolaDao.insert(item.toEntity())
    }

    suspend fun updateCarriola(item: Carriola) {
        carriolaDao.update(item.toEntity())
    }

    suspend fun deleteCarriola(id: Int) {
        carriolaDao.deleteById(id)
    }

    suspend fun seedCarriolaIfNeeded() {
        if (carriolaDao.count() == 0) {
            carriolaDao.insertAll(defaultCarriolas())
        }
    }

    fun getAccesorios(): Flow<List<Accesorios>> =
        accesorioDao.getAll().map { list -> list.map { it.toDomain() } }

    fun getAccesorioById(id: Int): Flow<Accesorios?> =
        accesorioDao.getById(id).map { it?.toDomain() }

    suspend fun insertAccesorio(item: Accesorios) {
        accesorioDao.insert(item.toEntity())
    }

    suspend fun updateAccesorio(item: Accesorios) {
        accesorioDao.update(item.toEntity())
    }

    suspend fun deleteAccesorio(id: Int) {
        accesorioDao.deleteById(id)
    }

    suspend fun seedAccesoriosIfNeeded() {
        if (accesorioDao.count() == 0) {
            accesorioDao.insertAll(defaultAccesorios())
        }
    }

    suspend fun seedAllIfNeeded() {
        seedRopaIfNeeded()
        seedCarriolaIfNeeded()
        seedAccesoriosIfNeeded()
    }

    private fun defaultRopa(): List<RopaEntity> = listOf(
        RopaEntity(
            id = 1,
            imagen = R.drawable.unisex,
            titulo = "Body algodón manga larga - Rosa con ositos",
            precio = "MXN 199.00",
            condicion = "Nuevo",
            caracteristicas = "Botones de presión en la entrepierna, costuras suaves, estampado resistente al lavado.",
            talla = "0-3 M",
            materiales = "Algodón 100% (peinado, hipoalergénico)",
            rangoEdad = "0 - 3 meses",
            metodoEnvio = "Envío a domicilio (Paquetería) — 3-5 días hábiles"
        ),
        RopaEntity(
            id = 2,
            imagen = R.drawable.pijama,
            titulo = "Set pijama 2 piezas - Estrellas",
            precio = "MXN 349.00",
            condicion = "Nuevo",
            caracteristicas = "Incluye pijama y gorro. Cierre con broches, tela suave para dormir.",
            talla = "3-6 M",
            materiales = "Algodón peinado 95% / Elastano 5%",
            rangoEdad = "3 - 6 meses",
            metodoEnvio = "Recoger en tienda / Envío estándar"
        ),
        RopaEntity(
            id = 3,
            imagen = R.drawable.unisex,
            titulo = "Enterizo sin mangas unisex - Beige",
            precio = "MXN 259.00",
            condicion = "Nuevo",
            caracteristicas = "Apertura inferior con broches, tejido transpirable.",
            talla = "6-9 M",
            materiales = "Algodón orgánico 100%",
            rangoEdad = "6 - 9 meses",
            metodoEnvio = "Envío a domicilio (Paquetería)"
        ),
        RopaEntity(
            id = 4,
            imagen = R.drawable.sudadera,
            titulo = "Sudadera con capucha 'Osito' - Gris",
            precio = "MXN 429.00",
            condicion = "Nuevo",
            caracteristicas = "Forro polar ligero, capucha con orejitas, cierre frontal.",
            talla = "9-12 M",
            materiales = "Poliéster 80% / Algodón 20%",
            rangoEdad = "9 - 12 meses",
            metodoEnvio = "Envío express disponible"
        )
    )

    private fun defaultCarriolas(): List<CarriolaEntity> = listOf(
        CarriolaEntity(
            id = 1,
            imagen = R.drawable.carriola,
            titulo = "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
            precio = "MXN 8,999.00",
            condicion = "Nueva (Certificada y Sellada)",
            caracteristicas = "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n - Amplia canasta de almacenamiento inferior.\n - Incluye: Portavasos y cubrepiés.",
            peso = "11.5 kg (Carriola con asiento)",
            materiales = "Chasis de aluminio ligero de alta resistencia y textiles hipoalergénicos",
            rangoEdad = "0 meses en adelante",
            metodoEnvio = "Envío terrestre gratuito a todo el país (3-5 días hábiles)"
        ),
        CarriolaEntity(
            id = 2,
            imagen = R.drawable.carriola1,
            titulo = "Carriola Ultra Ligera de Viaje",
            precio = "MXN 3,450.00",
            condicion = "Nueva (Certificada por fabricante)",
            caracteristicas = "- Diseño ultra compacto, apto para cabina de avión.\n- Peso pluma, fácil de transportar.",
            peso = "2.6 kg",
            materiales = "Marco de acero reforzado",
            rangoEdad = "A partir de los 6 meses",
            metodoEnvio = "Envío Express (24-48 horas)"
        )
    )

    private fun defaultAccesorios(): List<AccesorioEntity> = listOf(
        AccesorioEntity(
            id = 1,
            imagen = R.drawable.monitor,
            titulo = "Monitor de Bebé Inteligente con Cámara HD y Visión Nocturna",
            precio = "MXN 2,999.00",
            caracteristicas = "- Transmisión de video Full HD 1080p vía Wi-Fi a tu smartphone o tablet.- Detección de movimiento, llanto y temperatura ambiente.",
            materiales = "Plástico ABS resistente y componentes electrónicos",
            rangoEdad = "0 - 5 años",
            metodoEnvio = "Envío por servicio de mensajería (2 días hábiles) con seguro incluido"
        ),
        AccesorioEntity(
            id = 2,
            imagen = R.drawable.extractor,
            titulo = "Extractor de Leche Eléctrico Doble Manos Libres con 5 Modos",
            precio = "MXN 1,850.00",
            caracteristicas = "- Extracción doble para maximizar la producción de leche.\n    - 5 modos de succión y 9 niveles de intensidad ajustables.",
            materiales = "Silicona de grado alimenticio y polipropileno (Libre de BPA)",
            rangoEdad = "Para madres en periodo de lactancia",
            metodoEnvio = "Envío gratuito a domicilio (48 horas)"
        ),
        AccesorioEntity(
            id = 3,
            imagen = R.drawable.colchoneta,
            titulo = "Manta de Actividades y Gimnasio para Bebé con Alfombra Sensorial",
            precio = "MXN 980.00",
            caracteristicas = "\n    - Manta de gran tamaño con acolchado extra suave.\n    - Arcos flexibles con 5 juguetes colgantes desmontables (sonajeros, espejo seguro).",
            materiales = "Tejido de algodón orgánico, poliéster y plástico no tóxico",
            rangoEdad = "0- 12 meses",
            metodoEnvio = "Entrega en punto de recogida (Pick Up) sin costo o envío estándar económico."
        ),
        AccesorioEntity(
            id = 4,
            imagen = R.drawable.portabebes,
            titulo = "Portabebés Ergonómico 4-en-1 de Lujo para Recién Nacidos y Niños",
            precio = "MXN 1,599.00",
            caracteristicas = "\n    - Cuatro posiciones de transporte: frontal (mirando hacia adentro/afuera), cadera y espalda.\n    - Soporte lumbar acolchado para la comodidad del adulto.",
            materiales = "Algodón transpirable de alta calidad y hebillas de seguridad reforzadas",
            rangoEdad = "3.5 kg hasta 20 kg",
            metodoEnvio = "Envío urgente al día siguiente (Next Day) por MXN 100.00"
        )
    )
}

private fun RopaEntity.toDomain() = Ropa(
    id = id,
    imagen = imagen,
    TítuloProducto = titulo,
    Precio = precio,
    Condición = condicion,
    Características = caracteristicas,
    Talla = talla,
    Materiales = materiales,
    Rangoedad = rangoEdad,
    metodoEnvio = metodoEnvio
)

private fun Ropa.toEntity() = RopaEntity(
    id = if (id == 0) 0 else id,
    imagen = imagen,
    titulo = TítuloProducto,
    precio = Precio,
    condicion = Condición,
    caracteristicas = Características,
    talla = Talla,
    materiales = Materiales,
    rangoEdad = Rangoedad,
    metodoEnvio = metodoEnvio
)

private fun CarriolaEntity.toDomain() = Carriola(
    id = id,
    imagen = imagen,
    TítuloProducto = titulo,
    Precio = precio,
    Condición = condicion,
    Características = caracteristicas,
    Peso = peso,
    Materiales = materiales,
    Rangoedad = rangoEdad,
    metodoEnvio = metodoEnvio
)

private fun Carriola.toEntity() = CarriolaEntity(
    id = if (id == 0) 0 else id,
    imagen = imagen,
    titulo = TítuloProducto,
    precio = Precio,
    condicion = Condición,
    caracteristicas = Características,
    peso = Peso,
    materiales = Materiales,
    rangoEdad = Rangoedad,
    metodoEnvio = metodoEnvio
)

private fun AccesorioEntity.toDomain() = Accesorios(
    id = id,
    imagen = imagen,
    TítuloProducto = titulo,
    Precio = precio,
    Características = caracteristicas,
    Materiales = materiales,
    Rangoedad = rangoEdad,
    metodoEnvio = metodoEnvio
)

private fun Accesorios.toEntity() = AccesorioEntity(
    id = if (id == 0) 0 else id,
    imagen = imagen,
    titulo = TítuloProducto,
    precio = Precio,
    caracteristicas = Características,
    materiales = Materiales,
    rangoEdad = Rangoedad,
    metodoEnvio = metodoEnvio
)

