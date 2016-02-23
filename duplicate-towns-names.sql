select distinct(x.id), x.name, regions.name from 
    (select t1.* from towns t1, towns t2 where t1.name like t2.name and t1.id != t2.id) as x
join regions on (regions.id = x.region_id);

--RESULT:
--
--4285  Arroyomolinos               Madrid
--1645  Arroyomolinos               Cáceres
--2641  Cabanas                     Gerona
--2316  Cabañas                     La Coruña
--4672  Castejón                    Navarra
--2437  Castejón                    Cuenca
--4572  Cieza                       Murcia
--1911  Cieza                       Cantabria
--7216  El Campillo                 Valladolid
--3401  El Campillo                 Huelva
--7567  Fonfría                     Zamora
--6567  Fonfría                     Teruel
--6809  La Mata                     Toledo
--2059  La Mata                     Castellón
--7410  La Zarza                    Valladolid
--872   La Zarza                    Badajoz
--2497  Moya                        Cuenca
--5166  Moya                        Las Palmas
--1083  Moyá                        Barcelona
--4241  Nogales                     Lugo
--801   Nogales                     Badajoz
--6198  Rebollar                    Soria
--1777  Rebollar                    Cáceres
--5672  Sancti-Spíritus             Salamanca
--830   Sancti-Spíritus             Badajoz
--3912  Sobrado                     León
--2356  Sobrado                     La Coruña
--7719  Villaescusa                 Zamora
--1989  Villaescusa                 Cantabria
--7398  Villanueva de los Infantes  Valladolid
--2223  Villanueva de los Infantes  Ciudad Real
--5741  Villarmayor                 Salamanca
--2321  Villarmayor                 La Coruña
