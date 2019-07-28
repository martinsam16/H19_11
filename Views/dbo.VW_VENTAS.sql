SET QUOTED_IDENTIFIER ON
GO
SET ANSI_NULLS ON
GO
CREATE VIEW [dbo].[VW_VENTAS]
AS
SELECT venta.IDVEN as idven, venta.FECVEN as fecven, venta.ETSVEN as estven, venta.TOTVEN as totven, 
	comprador.IDPER as idComprador, comprador.NOMPER as nomComprador, comprador.APEPER as apeComprador, 
	vendedor.IDPER as idVendedor, vendedor.NOMPER as nomVendedor, vendedor.APEPER as apeVendedor, 
	sucursal.IDSUC as idsuc, sucursal.NOMSUC as nomsuc, 
	eq.IDEQ as ideq, eq.NOMEQ as nomeq, eq.MAREQ as mareq, eq.MODEQ as modeq, eq.PREEQ as preeq,
	detalle.IDVENDET as detId, detalle.CNTVEN as detalleCntV, detalle.TOTVENDET as detalleTotVen
FROM VENTA venta 
INNER JOIN VENTA_DETALLE detalle 
ON venta.IDVEN = detalle.IDVEN 
INNER JOIN PERSONA comprador 
ON venta.IDPER = comprador.IDPER 
INNER JOIN LOGIN login 
ON venta.IDLOG = login.IDLOG 
INNER JOIN TRABAJADOR empleado 
ON login.IDTRAB = empleado.IDTRAB 
INNER JOIN SUCURSAL sucursal 
ON empleado.IDSUC = sucursal.IDSUC 
INNER JOIN PERSONA vendedor 
ON empleado.IDPER = vendedor.IDPER 
INNER JOIN EQUIPO eq 
ON detalle.IDEQ = eq.IDEQ
GO
