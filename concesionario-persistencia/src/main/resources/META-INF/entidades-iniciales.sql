--
-- Dumping data for table `Marca`
--

LOCK TABLES `Marca` WRITE;
INSERT INTO `Marca` VALUES (1,'Peugeot','1950', 'Francia');
INSERT INTO `Marca` VALUES (2,'Hyundai','1950', 'Bélgica');
UNLOCK TABLES;

--
-- Dumping data for table `Modelo`
--

LOCK TABLES `TipoModelo` WRITE;
INSERT INTO `TipoModelo` VALUES (1,'Modelo Alta Gama'), (2,'Turismo'), (3, 'Todoterreno');
UNLOCK TABLES;

--
-- Dumping data for table `Modelo`
--

LOCK TABLES `Modelo` WRITE;
INSERT INTO `Modelo` VALUES (1,'2008',1,1),(2,'i20',2,2);
UNLOCK TABLES;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
INSERT INTO `Cliente` VALUES ('11111111A','codigoPostal1','domicilio1','localidad1','provincia1','telefono1','nombre1 apelido1'),('22222222B','codigoPostal2','domicilio2','localidad2','provincia2','telefono2','nombre2 apellido2'),('33333333C','codigoPostal3','domicilio3','localidad3','provincia3','telefono3','nombre3 apellido3');
UNLOCK TABLES;


--
-- Dumping data for table `PedidoCoche`
--

LOCK TABLES `PedidoCoche` WRITE;
INSERT INTO `PedidoCoche` VALUES (1,'2018-11-19',NULL ,'PENDIENTE','11111111A',1,1);
INSERT INTO `PedidoCoche` VALUES (2,'2018-11-19',NULL ,'ENTREGADO','11111111A',2,2);
UNLOCK TABLES;


