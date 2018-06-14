delimiter //
DROP TRIGGER IF EXISTS SumarExistencias//
CREATE TRIGGER SumarExistencias
AFTER INSERT on Lote
FOR EACH ROW
BEGIN
        
        
        
        Update	 Producto set Producto.Existencia=Producto.Existencia+new.Cantidad where Producto.id=new.Producto_id;
        
    END;//
delimiter ;  
delimiter //
DROP TRIGGER IF EXISTS RestarExistencias//
CREATE TRIGGER RestarExistencias
AFTER INSERT on LoteVenta
FOR EACH ROW
BEGIN
        
        
        
        Update	 Producto set Producto.Existencia=Producto.Existencia-new.Cantidad where Producto.id=new.Producto_id;
        
    END;//
delimiter ;  