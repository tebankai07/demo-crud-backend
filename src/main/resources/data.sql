-- Limpieza
DELETE FROM productos;
DELETE FROM categorias;
DELETE FROM user;
DELETE FROM rol;

-- Roles
INSERT INTO rol (id, name, description, created_at, updated_at) VALUES
 (1, 'USER', 'Rol estándar', NOW(), NOW()),
 (2, 'ADMIN', 'Rol con privilegios de gestión', NOW(), NOW()),
 (3, 'SUPER_ADMIN', 'Rol con privilegios totales', NOW(), NOW());

-- Usarios
INSERT INTO user (email, password, name, lastname, role_id, created_at, updated_at) VALUES
 ('superadmin@demo.com', '$2a$10$Tl1HQpJ5Kmnzu1UtUVlAeOk2JuaUMUYzxxblQndB35eo3wifoJuiK', 'Super', 'Admin', 3, NOW(), NOW()),
 ('usuario@demo.com',     '$2a$10$z1dR5u0r6Z9w4d9EMG3B3uAZXWfMZt/mCdxXUoTo1tKxv9t6B3n6O', 'Usuario', 'Normal', 1, NOW(), NOW());
