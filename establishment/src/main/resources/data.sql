-- Script de prueba para la tabla location

INSERT INTO location (
    id,
    name,
    address,
    city,
    state,
    country,
    zip,
    phone,
    capacity,
    available,
    description,
    type,
    created_at,
    image_url
) VALUES
      (
          '550e8400-e29b-41d4-a716-446655440000',
          'Oficina Central',
          'Av. Siempre Viva 742',
          'Springfield',
          'Illinois',
          'USA',
          '62704',
          '+1-555-123456',
          120,
          TRUE,
          'Oficina principal con salas de reuniones y estacionamiento',
          'HOTEL',
          '2025-09-03 10:00:00',
       'images/a6363156-812c-4e41-b17b-16aef595e715'
      ),
      (
          '123e4567-e89b-12d3-a456-426614174001',
          'Sucursal Norte',
          'Calle Norte 123',
          'Monterrey',
          'Nuevo León',
          'México',
          '64000',
          '+52-81-555-9876',
          80,
          TRUE,
          'Sucursal ubicada en la zona norte con capacidad mediana',
          'RESTAURANT',
          '2025-09-03 11:15:00',
          'images/a6363156-812c-4e41-b17b-16aef595e715'
      ),
      (
          '123e4567-e89b-12d3-a456-426614174002',
          'Sucursal Sur',
          'Av. del Sol 456',
          'Guadalajara',
          'Jalisco',
          'México',
          '44100',
          '+52-33-555-1122',
          60,
          FALSE,
          'Sucursal temporalmente cerrada por remodelación',
          'HOTEL',
          '2025-09-03 12:30:00',
          'images/a6363156-812c-4e41-b17b-16aef595e715'
      ),
      (
          '123e4567-e89b-12d3-a456-426614174003',
          'Centro de Distribución',
          'Parque Industrial 789',
          'Querétaro',
          'Querétaro',
          'México',
          '76100',
          '+52-442-555-3344',
          300,
          TRUE,
          'Centro logístico para almacenamiento y distribución',
          'RESTAURANT',
          '2025-09-03 14:00:00',
          'images/a6363156-812c-4e41-b17b-16aef595e715'
      ),
      (
          '123e4567-e89b-12d3-a456-426614174004',
          'Sucursal CDMX',
          'Av. Reforma 100',
          'Ciudad de México',
          'CDMX',
          'México',
          '06500',
          '+52-55-555-7788',
          150,
          TRUE,
          'Sucursal principal en Ciudad de México con amplio acceso',
          'HOTEL',
          '2025-09-03 15:30:00',
          'images/a6363156-812c-4e41-b17b-16aef595e715'
      );

INSERT INTO opinions (
    id, location_id, username, score, opinion_title, comment, created_at
) VALUES
      ('11111111-1111-1111-1111-111111111111','550e8400-e29b-41d4-a716-446655440000','juan.perez',5,'Excelente servicio','La atención en la oficina central fue de primera, muy recomendable.', CURRENT_TIMESTAMP),
      ('22222222-2222-2222-2222-222222222222','123e4567-e89b-12d3-a456-426614174001','maria.garcia',4,'Buena experiencia','La sucursal norte es cómoda y el personal muy amable.', CURRENT_TIMESTAMP),
      ('33333333-3333-3333-3333-333333333333','123e4567-e89b-12d3-a456-426614174002','carlos.lopez',2,'Cerrada por remodelación','Intenté visitar pero estaba cerrada, deberían avisar en la web.', CURRENT_TIMESTAMP),
      ('44444444-4444-4444-4444-444444444444','123e4567-e89b-12d3-a456-426614174003','ana.martinez',3,'Regular','El centro logístico cumple su función, aunque la atención fue un poco lenta.', CURRENT_TIMESTAMP),
      ('55555555-5555-5555-5555-555555555555','123e4567-e89b-12d3-a456-426614174004','pedro.sanchez',5,'Sucursal excelente','La sucursal de CDMX es amplia, moderna y con excelente ubicación.', CURRENT_TIMESTAMP);
