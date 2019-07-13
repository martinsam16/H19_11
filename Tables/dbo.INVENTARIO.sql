CREATE TABLE [dbo].[INVENTARIO]
(
[IDINV] [int] NOT NULL IDENTITY(1, 1),
[FECINV] [date] NOT NULL,
[TIPINV] [nchar] (1) COLLATE Modern_Spanish_CI_AS NOT NULL CONSTRAINT [DF_INVENTARIO_TIPINV] DEFAULT (N'E'),
[IDEQ] [int] NOT NULL,
[CNTINV] [int] NOT NULL,
[ESTINV] [nchar] (1) COLLATE Modern_Spanish_CI_AS NOT NULL CONSTRAINT [DF_INVENTARIO_ESTINV] DEFAULT (N'A')
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[INVENTARIO] ADD CONSTRAINT [PK_INVENTARIO] PRIMARY KEY CLUSTERED  ([IDINV]) ON [PRIMARY]
GO
ALTER TABLE [dbo].[INVENTARIO] ADD CONSTRAINT [EQUIPO_INVENTARIO_IDEQ] FOREIGN KEY ([IDEQ]) REFERENCES [dbo].[EQUIPO] ([IDEQ])
GO
