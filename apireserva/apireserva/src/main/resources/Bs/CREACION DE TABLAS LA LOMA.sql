Create table mesa(
id_mesa bigserial primary key,
num_mesa integer not null,
capacidad integer not null,
ubicacion varchar(80),
-- Se cambio estado por estado_mesa
estado_mesa varchar(20) not null,
created_at timestamp(0),

check (estado_mesa in ('Disponible', 'Ocupada', 'Fuera de Servicio'))
);

create table empleado (
id_empleado bigserial primary key,
-- Se cambio nombre por nombre_empleado
nombre_empleado varchar(120) not null,
puesto varchar(80),
created_at timestamp(0)

check (puesto in ('Mesero', 'Cocinero', 'Recepcionista', 'Cajero', 'Gerente'))
);

create table cliente (
id_cliente bigserial primary key,
-- Se le cambio nombre por nombre_cliente
nombre_cliente varchar(120) not null,
correo varchar(160),
telefono varchar(30),
created_at timestamp(0)
);

create table producto (
id_producto bigserial primary key,
-- Se cambio nombre por nombre_estado
nombre_producto varchar(120) not null,
descripcion text,
precio decimal(10, 2) not null,
categoria varchar(80),
activo boolean not null,
created_at timestamp(0)
);

create table reserva (
id_reserva bigserial primary key,
id_cliente bigint not null,
id_mesa bigint not null,
-- Se cambio fecha por fecha_reservada
fecha_reservada timestamp(0) not null,
turno varchar(12) not null,
-- En personas se hizo el cambio a not null
personas integer not null,
-- Se cambio estado por estado_reserva
estado_reserva varchar(20) not null,
created_at timestamp(0),

foreign key (id_cliente) references cliente(id_cliente),
foreign key (id_mesa) references mesa(id_mesa),

check (estado_reserva in ('Confirmada', 'Pendiente', 'Cancelada', 'Completada')),
check (turno in ('Mañana', 'Tarde', 'Noche'))
);

create table pedido (
id_pedido bigserial primary key,
id_cliente bigint not null,
id_empleado bigint not null,
-- En id_mesa se hizo el cambio a not null
id_mesa bigint not null,
fecha_hora timestamp not null,
-- Se cambio estado por estado_pedido
estado_pedido varchar(20) not null,
created_at timestamp(0),

foreign key (id_cliente) references cliente(id_cliente),
foreign key (id_empleado) references empleado(id_empleado),
foreign key (id_mesa) references mesa(id_mesa),

check (estado_pedido in ('Pendiente', 'En Proceso', 'Listo para Servir', 'Servido', 'Cancelado'))
);

create table consumo (
id_detalle bigserial primary key,
id_pedido bigint not null,
id_producto bigint not null,
cantidad int not null,
precio_unitario decimal(10,2) not null,

-- restricción para evitar duplicados en el mismo pedido
unique (id_pedido, id_producto),

foreign key (id_pedido) references pedido(id_pedido),
foreign key (id_producto) references producto(id_producto)
);

create table factura (
id_factura bigserial primary key,
id_pedido bigint not null unique,
subtotal decimal(10,2) not null,
-- En descuento se quito la restricción de not null
descuento decimal(10,2),
propina decimal(10,2) not null,
impuestos decimal(10,2) not null,
total decimal(10,2) not null,
-- Se agrego el estado_factura
estado_factura varchar(20) not null default 'Pendiente de Pago',
created_at timestamp(0),

foreign key (id_pedido) references pedido(id_pedido),

check (estado_factura in ('Pendiente de Pago', 'Pagada', 'Anulada'))
);

create table pago (
id_pago bigserial primary key,
id_factura bigint not null,
monto decimal(10,2) not null,
metodo_pago varchar(30) not null,
-- Se cambio fecha por fecha_pago
fecha_pago timestamp not null,
refecencia varchar(120),

foreign key (id_factura) references factura(id_factura),

check (metodo_pago in ('Efectivo', 'Tarjeta', 'Bitcoin'))
);

create table cierra_caja (
id_cierra bigserial primary key,
id_empleado bigint not null,
fecha_cierre timestamp not null,
total_ventas decimal(10,2) not null, 
observaciones text,

foreign key (id_empleado) references empleado(id_empleado)
);

