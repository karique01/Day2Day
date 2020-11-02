package com.karique.work.dev.day2day.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegionesPeru {

    public static List<String> getDepartamentos(){
        return Arrays.asList("Amazonas", "Ancash", "Apurimac", "Arequipa", "Ayacucho", "Cajamarca", "Callao", "Cusco", "Huancavelica", "Huanuco", "Ica", "Junin", "La Libertad", "Lambayeque", "Lima", "Loreto", "Madre De Dios", "Moquegua", "Pasco", "Piura", "Puno", "San Martin", "Tacna", "Tumbes", "Ucayali");
    }
    public static List<String> getProvincias(String departamento){
        switch (departamento){
            case "Amazonas":
                return Arrays.asList("Asunción", "Balsas", "Chachapoyas", "Cheto", "Chiliquín", "Chuquibamba", "Granada", "Huancas", "La Jalca", "Leimebamba", "Levanto", "Magdalena", "Mariscal Castilla", "Molinopampa", "Montevideo", "Olleros", "Quinjalca", "San Francisco de Daguas", "San Isidro de Maino", "Soloco", "Sonche");
            case "Ancash":
                return Arrays.asList("Aija", "Antonio Raymondi", "Asunción", "Bolognesi", "Carhuaz", "Carlos F. Fitzcarrald", "Casma", "Corongo", "Huaraz", "Huari", "Huarmey", "Huaylas", "Mariscal Luzuriaga", "Ocros", "Pallasca", "Pomabamba", "Recuay", "Santa", "Sihuas", "Yungay");
            case "Apurimac":
                return Arrays.asList("Abancay", "Antabamba", "Aymaraes", "Cotabambas", "Grau", "Chincheros", "Andahuaylas");
            case "Arequipa":
                return Arrays.asList("Arequipa", "Camaná", "Caravelí", "Castilla", "Caylloma", "Condesuyos", "Islay", "La Unión");
            case "Ayacucho":
                return Arrays.asList("Cangallo", "Huanta", "Huamanga", "Huanca Sancos", "La Mar", "Lucanas", "Parinacochas", "Páucar del Sara Sara", "Sucre", "Víctor Fajardo", "Vilcashuamán");
            case "Cajamarca":
                return Arrays.asList("Cajamarca", "Cajabamba", "Celendín", "Chota", "Contumazá", "Cutervo", "Hualgayoc", "Jaén", "San Ignacio", "San Marcos", "San Miguel", "San Pablo", "Santa Cruz");
            case "Callao":
                return Arrays.asList("Callao");
            case "Cusco":
                return Arrays.asList("Cusco", "Acomayo", "Anta", "Calca", "Canas", "Canchis", "Chumbivilcas", "Espinar", "La Convención", "Paruro", "Paucartambo", "Quispicanchi", "Urubamba");
            case "Huancavelica":
                return Arrays.asList("Huancavelica", "Acobamba", "Angaraes", "Castrovirreyna", "Churcampa", "Huaytará", "Tayacaja");
            case "Huanuco":
                return Arrays.asList("Huánuco", "Ambo", "Dos de Mayo", "Huacaybamba", "Huamalíes", "Leoncio Prado", "Marañón", "Pachitea", "Puerto Inca", "Lauricocha", "Yarowilca");
            case "Ica":
                return Arrays.asList("Ica", "Chincha", "Nazca", "Palpa", "Pisco");
            case "Junin":
                return Arrays.asList("Chanchamayo", "Chupaca", "Concepción", "Huancayo", "Jauja", "Junín", "Satipo", "Tarma", "Yauli");
            case "La Libertad":
                return Arrays.asList("Trujillo", "Ascope", "Bolívar", "Chepén", "Julcán", "Otuzco", "Gran Chimú", "Pacasmayo", "Pataz", "Sánchez Carrión", "Santiago de Chuco", "Virú");
            case "Lambayeque":
                return Arrays.asList("Chiclayo", "Ferreñafe", "Lambayeque");
            case "Lima":
                return Arrays.asList("Barranca", "Cajatambo", "Canta", "Cañete", "Huaral", "Huarochirí", "Huaura", "Lima", "Oyón", "Yauyos");
            case "Loreto":
                return Arrays.asList("Maynas", "Putumayo", "Alto Amazonas", "Loreto", "Mariscal Ramón Castilla", "Requena", "Ucayali", "Datem del Marañón");
            case "Madre De Dios":
                return Arrays.asList("Tambopata", "Manu", "Tahuamanu");
            case "Moquegua":
                return Arrays.asList("Mariscal Nieto", "General Sánchez Cerro", "Ilo");
            case "Pasco":
                return Arrays.asList("Pasco", "Oxapampa", "Daniel A. Carrión");
            case "Piura":
                return Arrays.asList("Ayabaca", "Huancabamba", "Morropón", "Piura", "Sechura", "Sullana", "Paita", "Talara");
            case "Puno":
                return Arrays.asList("San Román", "Puno", "Azángaro", "Chucuito", "El Collao", "Melgar", "Carabaya", "Huancané", "Sandia", "San Antonio de Putina", "Lampa", "Yunguyo", "Moho");
            case "San Martin":
                return Arrays.asList("Bellavista", "El Dorado", "Huallaga", "Lamas", "Mariscal Cáceres", "Moyobamba", "Picota", "Rioja", "San Martín", "Tocache");
            case "Tacna":
                return Arrays.asList("Tacna", "Candarave", "Jorge Basadre", "Tarata");
            case "Tumbes":
                return Arrays.asList("Tumbes", "Zarumilla", "Contralmirante Villar");
            case "Ucayali":
                return Arrays.asList("Coronel Portillo", "Atalaya", "Padre Abad", "Purús");
        }
        return Collections.singletonList("");
    }
    public static List<String> getDistritos(String provincia){
        switch (provincia) {
            case "Chachapoyas":
                return Arrays.asList("Asunción", "Balsas", "Cheto", "Chiliquin", "Chuquibamba", "Granada", "Huancas", "La Jalca", "Leimebamba", "Levanto", "Magdalena", "Mariscal Castilla", "Molinopampa", "Montevideo", "Olleros", "Quinjalca", "San Francisco de Daguas", "San Isidro de Maino", "Soloco", "Sonche");
            case "Bagua":
                return Arrays.asList("Aramango", "Copallin", "El Parco", "Imaza", "La Peca");
            case "Bongará":
                return Arrays.asList("Jumbilla", "Chisquilla", "Churuja", "Corosha", "Cuispes", "Florida", "Jazan", "Recta", "San Carlos", "Shipasbamba", "Valera", "Yambrasbamba");
            case "Condorcanqui":
                return Arrays.asList("Nieva", "El Cenepa", "Río Santiago");
            case "Luya":
                return Arrays.asList("Lamud", "Camporredondo", "Cocabamba", "Colcamar", "Conila", "Inguilpata", "Longuita", "Lonya Chico", "Luya", "Luya Viejo", "María", "Ocalli", "Ocumal", "Pisuquia", "Providencia", "San Cristóbal", "San Francisco de Yeso", "San Jerónimo", "San Juan de Lopecancha", "Santa Catalina", "Santo Tomas", "Tingo", "Trita");
            case "Rodríguez de Mendoza":
                return Arrays.asList("San Nicolás", "Chirimoto", "Cochamal", "Huambo", "Limabamba", "Longar", "Mariscal Benavides", "Milpuc", "Omia", "Santa Rosa", "Totora", "Vista Alegre");
            case "Utcubamba":
                return Arrays.asList("Bagua Grande", "Cajaruro", "Cumba", "El Milagro", "Jamalca", "Lonya Grande", "Yamon");
            case "Aija":
                return Arrays.asList("Aija", "Coris", "Huacllan", "La Merced", "Succha");
            case "Antonio Raymondi":
                return Arrays.asList("Llamellin", "Aczo", "Chaccho", "Chingas", "Mirgas", "San Juan de Rontoy");
            case "Asunción":
                return Arrays.asList("Chacas", "Acochaca");
            case "Bolognesi":
                return Arrays.asList("Chiquian", "Abelardo Pardo Lezameta", "Antonio Raymondi", "Aquia", "Cajacay", "Canis", "Colquioc", "Huallanca", "Huasta", "Huayllacayan", "La Primavera", "Mangas", "Pacllon", "San Miguel de Corpanqui", "Ticllos");
            case "Carhuaz":
                return Arrays.asList("Carhuaz","Acopampa");
            case "Carlos F. Fitzcarrald":
                return Arrays.asList("San Luis", "San Nicolás", "Yauya");
            case "Casma":
                return Arrays.asList("Casma", "Buena Vista Alta", "Comandante Noel", "Yautan");
            case "Corongo":
                return Arrays.asList("Corongo", "Aco", "Bambas", "Cusca", "La Pampa", "Yanac", "Yupan");
            case "Huaraz":
                return Arrays.asList("Huaraz", "Cochabamba", "Colcabamba", "Huanchay", "Independencia", "Jangas", "La Libertad", "Olleros", "Pampas Grande", "Pariacoto", "Pira", "Tarica");
            case "Huari":
                return Arrays.asList("Huari", "Anra", "Cajay", "Chavin de Huantar", "Huacachi", "Huacchis", "Huachis", "Huantar", "Masin", "Paucas", "Ponto", "Rahuapampa", "Rapayan", "San Marcos", "San Pedro de Chana", "Uco");
            case "Huarmey":
                return Arrays.asList("Huarmey", "Cochapeti", "Culebras", "Huayan", "Malvas");
            case "Huaylas":
                return Arrays.asList("Caraz", "Huallanca", "Huata", "Huaylas", "Mato", "Pamparomas", "Pueblo Libre", "Santa Cruz", "Santo Toribio", "Yuracmarca");
            case "Mariscal Luzuriaga":
                return Arrays.asList("Piscobamba", "Casca", "Eleazar Guzmán Barron", "Fidel Olivas Escudero", "Llama", "Llumpa", "Lucma", "Musga");
            case "Ocros":
                return Arrays.asList("Ocros", "Acas", "Cajamarquilla", "Carhuapampa", "Cochas", "Congas", "Llipa", "San Cristóbal de Rajan", "San Pedro", "Santiago de Chilcas");
            case "Pallasca":
                return Arrays.asList("Cabana", "Bolognesi", "Conchucos", "Huacaschuque", "Huandoval", "Lacabamba", "Llapo", "Pallasca", "Pampas", "Santa Rosa", "Tauca");
            case "Pomabamba":
                return Arrays.asList("Pomabamba", "Huayllan", "Parobamba", "Quinuabamba");
            case "Recuay":
                return Arrays.asList("Recuay", "Catac", "Cotaparaco", "Huayllapampa", "Llacllin", "Marca", "Pampas Chico", "Pararin", "Tapacocha", "Ticapampa");
            case "Santa":
                return Arrays.asList("Chimbote", "Cáceres del Perú", "Coishco", "Macate", "Moro", "Nepeña", "Samanco", "Santa", "Nuevo Chimbote");
            case "Sihuas":
                return Arrays.asList("Sihuas", "Acobamba", "Alfonso Ugarte", "Cashapampa", "Chingalpo", "Huayllabamba", "Quiches", "Ragash", "San Juan", "Sicsibamba");
            case "Yungay":
                return Arrays.asList("Yungay", "Cascapara", "Mancos", "Matacoto", "Quillo", "Ranrahirca", "Shupluy", "Yanama");
            case "Abancay":
                return Arrays.asList("Abancay", "Chacoche", "Circa", "Curahuasi", "Huanipaca", "Lambrama", "Pichirhua", "San Pedro de Cachora", "Tamburco");
            case "Antabamba":
                return Arrays.asList("Antabamba", "El Oro", "Huaquirca", "Juan Espinoza Medrano", "Oropesa", "Pachaconas", "Sabaino");
            case "Aymaraes":
                return Arrays.asList("Chalhuanca", "Capaya", "Caraybamba", "Chapimarca", "Colcabamba", "Cotaruse", "Ihuayllo", "Justo Apu Sahuaraura");
            case "Cotabambas":
                return Arrays.asList("Tambobamba", "Cotabambas", "Coyllurqui", "Haquira", "Mara", "Challhuahuacho");
            case "Grau":
                return Arrays.asList("Chuquibambilla", "Curpahuasi", "Gamarra", "Huayllati", "Mamara", "Micaela Bastidas", "Pataypampa", "Progreso", "San Antonio", "Santa Rosa", "Turpay", "Vilcabamba", "Virundo", "Curasco");
            case "Chincheros":
                return Arrays.asList("Chincheros", "Anco_Huallo", "Cocharcas", "Huaccana", "Ocobamba", "Ongoy", "Uranmarca", "Ranracancha", "Rocchacc", "El Porvenir", "Los Chankas");
            case "Andahuaylas":
                return Arrays.asList("Andahuaylas", "Andarapa", "Chiara", "Huancarama", "Huancaray", "Huayana", "Kishuara", "Pacobamba", "Pacucha", "Pampachiri", "Pomacocha", "San Antonio de Cachi", "San Jerónimo", "San Miguel de Chaccrampa", "Santa María de Chicmo", "Talavera", "Tumay Huaraca", "Turpo", "Kaquiabamba", "José María Arguedas");
            case "Arequipa":
                return Arrays.asList("Arequipa", "Alto Selva Alegre", "Cayma", "Cerro Colorado", "Characato", "Chiguata", "Jacobo Hunter", "La Joya", "Mariano Melgar", "Miraflores", "Mollebaya", "Paucarpata", "Pocsi", "Polobaya", "Quequeña", "Sabandia", "Sachaca", "San Juan de Siguas", "San Juan de Tarucani", "Santa Isabel de Siguas", "Santa Rita de Siguas", "Socabaya", "Tiabaya", "Uchumayo", "Vitor", "Yanahuara", "Yarabamba", "Yura", "José Luis Bustamante Y Rivero");
            case "Camaná":
                return Arrays.asList("Camaná", "José María Quimper", "Mariano Nicolás Valcárcel", "Mariscal Cáceres", "Nicolás de Pierola", "Ocoña", "Quilca", "Samuel Pastor");
            case "Caravelí":
                return Arrays.asList("Caravelí", "Acarí", "Atico", "Atiquipa", "Bella Unión", "Cahuacho", "Chala", "Chaparra", "Huanuhuanu", "Jaqui", "Lomas", "Quicacha", "Yauca");
            case "Castilla":
                return Arrays.asList("Aplao", "Andagua", "Ayo", "Chachas", "Chilcaymarca", "Choco", "Huancarqui", "Machaguay", "Orcopampa", "Pampacolca", "Tipan", "Uñon", "Uraca", "Viraco");
            case "Caylloma":
                return Arrays.asList("Chivay", "Achoma", "Cabanaconde", "Callalli", "Caylloma", "Coporaque", "Huambo", "Huanca", "Ichupampa", "Lari", "Lluta", "Maca", "Madrigal", "San Antonio de Chuca", "Sibayo", "Tapay", "Tisco", "Tuti", "Yanque", "Majes");
            case "Condesuyos":
                return Arrays.asList("Chuquibamba", "Andaray", "Cayarani", "Chichas", "Iray", "Río Grande", "Salamanca", "Yanaquihua");
            case "Islay":
                return Arrays.asList("Mollendo", "Cocachacra", "Dean Valdivia", "Islay", "Mejia", "Punta de Bombón");
            case "La Unión":
                return Arrays.asList("Cotahuasi", "Alca", "Charcana", "Huaynacotas", "Pampamarca", "Puyca", "Quechualla", "Sayla", "Tauria", "Tomepampa", "Toro");
            case "Cangallo":
                return Arrays.asList("Cangallo", "Chuschi", "Los Morochucos", "María Parado de Bellido", "Paras", "Totos");
            case "Huanta":
                return Arrays.asList("Huanta", "Ayahuanco", "Huamanguilla", "Iguain", "Luricocha", "Santillana", "Sivia", "Llochegua", "Canayre", "Uchuraccay", "Pucacolpa", "Chaca");
            case "Huamanga":
                return Arrays.asList("Ayacucho", "Acocro", "Acos Vinchos", "Carmen Alto", "Chiara", "Ocros", "Pacaycasa", "Quinua", "San José de Ticllas", "San Juan Bautista", "Santiago de Pischa", "Socos", "Tambillo", "Vinchos", "Jesús Nazareno", "Andrés Avelino Cáceres Dorregaray");
            case "Huanca Sancos":
                return Arrays.asList("Sancos", "Carapo", "Sacsamarca", "Santiago de Lucanamarca");
            case "La Mar":
                return Arrays.asList("San Miguel", "Anco", "Ayna", "Chilcas", "Chungui", "Luis Carranza", "Santa Rosa", "Tambo", "Samugari", "Anchihuay", "Oronccoy");
            case "Lucanas":
                return Arrays.asList("Puquio", "Aucara", "Cabana", "Carmen Salcedo", "Chaviña", "Chipao", "Huac-Huas", "Laramate", "Leoncio Prado", "Llauta", "Lucanas", "Ocaña", "Otoca", "Saisa", "San Cristóbal", "San Juan", "San Pedro", "San Pedro de Palco", "Sancos", "Santa Ana de Huaycahuacho", "Santa Lucia");
            case "Parinacochas":
                return Arrays.asList("Coracora","Chumpi","Coronel Castañeda","Pacapausa","Pullo","Puyusca","San Francisco de Ravacayco","Upahuacho");
            case "Páucar del Sara Sara":
                return Arrays.asList("Pausa","Colta","Corculla","Lampa","Marcabamba","Oyolo","Pararca","San Javier de Alpabamba","San José de Ushua","Sara Sara");
            case "Sucre":
                return Arrays.asList("Querobamba", "Belén", "Chalcos", "Chilcayoc", "Huacaña", "Morcolla", "Paico", "San Pedro de Larcay", "San Salvador de Quije", "Santiago de Paucaray", "Soras");
            case "Víctor Fajardo":
                return Arrays.asList("Huancapi","Alcamenca","Apongo","Asquipata","Canaria","Cayara","Colca","Huamanquiquia","Huancaraylla","Hualla","Sarhua","Vilcanchos");
            case "Vilcashuamán":
                return Arrays.asList("Vilcas Huaman","Accomarca","Carhuanca","Concepción","Huambalpa","Independencia","Saurama","Vischongo");
            case "Cajamarca":
                return Arrays.asList("Cajamarca","Asunción","Chetilla","Cospan","Encañada","Jesús","Llacanora","Los Baños del Inca","Magdalena","Matara","Namora","San Juan");
            case "Cajabamba":
                return Arrays.asList("Cajabamba","Cachachi","Condebamba","Sitacocha");
            case "Celendín":
                return Arrays.asList("Celendín","Chumuch","Cortegana","Huasmin","Jorge Chávez","José Gálvez","Miguel Iglesias","Oxamarca","Sorochuco","Sucre","Utco","LaLibertad de Pallan");
            case "Chota":
                return Arrays.asList("Chota","Anguia","Chadin","Chiguirip","Chimban","Choropampa","Cochabamba","Conchan","Huambos","Lajas","Llama","Miracosta","Paccha","Pion","Querocoto","San Juan de Licupis","Tacabamba","Tocmoche","Chalamarca");
            case "Contumazá":
                return Arrays.asList("Contumaza","Chilete","Cupisnique","Guzmango","San Benito","Santa Cruz de Toledo","Tantarica","Yonan");
            case "Cutervo":
                return Arrays.asList("Cutervo","Callayuc","Choros","Cujillo","La Ramada","Pimpingos","Querocotillo","San Andrés de Cutervo","San Juan de Cutervo","San Luis de Lucma","Santa Cruz","Santo Domingo de la Capilla","Santo Tomas","Socota","Toribio Casanova");
            case "Hualgayoc":
                return Arrays.asList("Bambamarca","Chugur","Hualgayoc");
            case "Jaén":
                return Arrays.asList("Jaén","Bellavista","Chontali","Colasay","Huabal","Las Pirias","Pomahuaca","Pucara","Sallique","San Felipe","San José del Alto","Santa Rosa");
            case "San Ignacio":
                return Arrays.asList("San Ignacio","Chirinos","Huarango","La Coipa","Namballe","San José de Lourdes","Tabaconas");
            case "San Marcos":
                return Arrays.asList("Pedro Gálvez","Chancay","Eduardo Villanueva","Gregorio Pita","Ichocan","José Manuel Quiroz","José Sabogal");
            case "San Miguel":
                return Arrays.asList("San Miguel","Bolívar","Calquis","Catilluc","El Prado","La Florida","Llapa","Nanchoc","Niepos","San Gregorio","San Silvestre de Cochan","Tongod","Unión Agua Blanca");
            case "San Pablo":
                return Arrays.asList("San Pablo","San Bernardino","San Luis","Tumbaden");
            case "Santa Cruz":
                return Arrays.asList("Santa Cruz","Andabamba","Catache","Chancaybaños","La Esperanza","Ninabamba","Pulan","Saucepampa","Sexi","Uticyacu","Yauyucan");
            case "Callao":
                return Arrays.asList("Callao");
            case "Cusco":
                return Arrays.asList("Cusco","Ccorca","Poroy","San Jerónimo","San Sebastian","Santiago","Saylla","Wanchaq");
            case "Acomayo":
                return Arrays.asList("Acomayo","Acopia","Acos","Mosoc Llacta","Pomacanchi","Rondocan","Sangarara");
            case "Anta":
                return Arrays.asList("Anta","Ancahuasi","Cachimayo","Chinchaypujio","Huarocondo","Limatambo","Mollepata","Pucyura","Zurite");
            case "Calca":
                return Arrays.asList("Calca","Coya","Lamay","Lares","Pisac","San Salvador","Taray","Yanatile");
            case "Canas":
                return Arrays.asList("Yanaoca","Checca","Kunturkanki","Langui","Layo","Pampamarca","Quehue","Tupac Amaru");
            case "Canchis":
                return Arrays.asList("Sicuani", "Checacupe", "Combapata", "Marangani", "Pitumarca", "San Pablo", "San Pedro", "Tinta");
            case "Chumbivilcas":
                return Arrays.asList("Santo Tomas","Capacmarca","Chamaca","Colquemarca","Livitaca","Llusco","Quiñota","Velille");
            case "Espinar":
                return Arrays.asList("Espinar","Condoroma","Coporaque","Ocoruro","Pallpata","Pichigua","Suyckutambo","Alto Pichigua");
            case "La Convención":
                return Arrays.asList("Santa Ana","Echarate","Huayopata","Maranura","Ocobamba","Quellouno","Kimbiri","Santa Teresa","Vilcabamba","Pichari","Inkawasi","Villa Virgen","Villa Kintiarina","Megantoni");
            case "Paruro":
                return Arrays.asList("Paruro","Accha","Ccapi","Colcha","Huanoquite","Omacha","Paccaritambo","Pillpinto","Yaurisque");
            case "Paucartambo":
                return Arrays.asList("Paucartambo","Caicay","Challabamba","Colquepata","Huancarani","Kosñipata");
            case "Quispicanchi":
                return Arrays.asList("Urcos","Andahuaylillas","Camanti","Ccarhuayo","Ccatca","Cusipata","Huaro","Lucre","Marcapata","Ocongate","Oropesa","Quiquijana");
            case "Urubamba":
                return Arrays.asList("Urubamba","Chinchero","Huayllabamba","Machupicchu","Maras","Ollantaytambo","Yucay");
            case "Huancavelica":
                return Arrays.asList("Huancavelica","Acobambilla","Acoria","Conayca","Cuenca","Huachocolpa","Huayllahuara","Izcuchaca","Laria","Manta","Mariscal Cáceres","Moya","Nuevo Occoro","Palca","Pilchaca","Vilca","Yauli","Ascensión","Huando");
            case "Acobamba":
                return Arrays.asList("Acobamba","Andabamba","Anta","Caja","Marcas","Paucara","Pomacocha","Rosario");
            case "Angaraes":
                return Arrays.asList("Lircay","Anchonga","Callanmarca","Ccochaccasa","Chincho","Congalla","Huanca-Huanca","Huayllay Grande","Julcamarca","San Antonio de Antaparco","Santo Tomas de Pata","Secclla");
            case "Castrovirreyna":
                return Arrays.asList("Castrovirreyna","Arma","Aurahua","Capillas","Chupamarca","Cocas","Huachos","Huamatambo","Mollepampa","San Juan","Santa Ana","Tantara","Ticrapo");
            case "Churcampa":
                return Arrays.asList("Churcampa","Anco","Chinchihuasi","El Carmen","La Merced","Locroja","Paucarbamba","San Miguel de Mayocc","San Pedro de Coris","Pachamarca","Cosme");
            case "Huaytará":
                return Arrays.asList("Huaytara","Ayavi","Córdova","Huayacundo Arma","Laramarca","Ocoyo","Pilpichaca","Querco","Quito-Arma","San Antonio de Cusicancha","San Francisco de Sangayaico","San Isidro","Santiago de Chocorvos","Santiago de Quirahuara","Santo Domingo de Capillas","Tambo");
            case "Tayacaja":
                return Arrays.asList("Pampas","Acostambo","Acraquia","Ahuaycha","Colcabamba","Daniel Hernández","Huachocolpa","Huaribamba","Ñahuimpuquio","Pazos","Quishuar","Salcabamba","Salcahuasi","San Marcos de Rocchac","Surcubamba","Tintay Puncu","Quichuas","Andaymarca","Roble","Pichos","Santiago de Tucuma");
            case "Huánuco":
                return Arrays.asList("Huanuco","Amarilis","Chinchao","Churubamba","Margos","Quisqui (Kichki)","San Francisco de Cayran","San Pedro de Chaulan","Santa María del Valle","Yarumayo","Pillco Marca","Yacus","San Pablo de Pillao");
            case "Ambo":
                return Arrays.asList("Ambo","Cayna","Colpas","Conchamarca","Huacar","San Francisco","San Rafael","Tomay Kichwa");
            case "Dos de Mayo":
                return Arrays.asList("La Unión","Chuquis","Marías","Pachas","Quivilla","Ripan","Shunqui","Sillapata","Yanas");
            case "Huacaybamba":
                return Arrays.asList("Huacaybamba","Canchabamba","Cochabamba","Pinra");
            case "Huamalíes":
                return Arrays.asList("Llata","Arancay","Chavín de Pariarca","Jacas Grande","Jircan","Miraflores","Monzón","Punchao","Puños","Singa","Tantamayo");
            case "Leoncio Prado":
                return Arrays.asList("Rupa-Rupa", "Daniel Alomía Robles", "Hermílio Valdizan", "José Crespo y Castillo", "Luyando", "Mariano Damaso Beraun", "Pucayacu", "Castillo Grande", "Pueblo Nuevo", "Santo Domingo de Anda");
            case "Marañón":
                return Arrays.asList("Huacrachuco","Cholon","San Buenaventura","La Morada","Santa Rosa de Alto Yanajanca");
            case "Pachitea":
                return Arrays.asList("Panao","Chaglla","Molino","Umari");
            case "Puerto Inca":
                return Arrays.asList("Puerto Inca","Codo del Pozuzo","Honoria","Tournavista","Yuyapichis");
            case "Lauricocha":
                return Arrays.asList("Jesús","Baños","Jivia","Queropalca","Rondos","San Francisco de Asís","San Miguel de Cauri");
            case "Yarowilca":
                return Arrays.asList("Chavinillo","Cahuac","Chacabamba","Aparicio Pomares","Jacas Chico","Obas","Pampamarca","Choras");
            case "Ica":
                return Arrays.asList("Ica","La Tinguiña","Los Aquijes","Ocucaje","Pachacutec","Parcona","Pueblo Nuevo","Salas","San José de Los Molinos","San Juan Bautista","Santiago","Subtanjalla","Tate","Yauca del Rosario");
            case "Chincha":
                return Arrays.asList("Chincha Alta","Alto Laran","Chavin","Chincha Baja","El Carmen","Grocio Prado","Pueblo Nuevo","San Juan de Yanac","San Pedro de Huacarpana","Sunampe","Tambo de Mora");
            case "Nazca":
                return Arrays.asList("Nasca","Changuillo","El Ingenio","Marcona","Vista Alegre");
            case "Palpa":
                return Arrays.asList("Palpa","Llipata","Río Grande","Santa Cruz","Tibillo");
            case "Pisco":
                return Arrays.asList("Pisco","Huancano","Humay","Independencia","Paracas","San Andrés","San Clemente","Tupac Amaru Inca");
            case "Chanchamayo":
                return Arrays.asList("Chanchamayo","Perene","Pichanaqui","San Luis de Shuaro","San Ramón","Vitoc");
            case "Chupaca":
                return Arrays.asList("Chupaca","Ahuac","Chongos Bajo","Huachac","Huamancaca Chico","San Juan de Iscos","San Juan de Jarpa","Tres de Diciembre","Yanacancha");
            case "Concepción":
                return Arrays.asList("Concepción","Aco","Andamarca","Chambara","Cochas","Comas","Heroínas Toledo","Manzanares","Mariscal Castilla","Matahuasi","Mito","Nueve de Julio","Orcotuna","San José de Quero","Santa Rosa de Ocopa");
            case "Huancayo":
                return Arrays.asList("Huancayo","Carhuacallanga","Chacapampa","Chicche","Chilca","Chongos Alto","Chupuro","Colca","Cullhuas","El Tambo","Huacrapuquio","Hualhuas","Huancan","Huasicancha","Huayucachi","Ingenio","Pariahuanca","Pilcomayo","Pucara","Quichuay","Quilcas","San Agustín","San Jerónimo de Tunan","Saño","Sapallanga","Sicaya","Santo Domingo de Acobamba","Viques");
            case "Jauja":
                return Arrays.asList("Jauja","Acolla","Apata","Ataura","Canchayllo","Curicaca","El Mantaro","Huamali","Huaripampa","Huertas","Janjaillo","Julcán","Leonor Ordóñez","Llocllapampa","Marco","Masma","Masma Chicche","Molinos","Monobamba","Muqui","Muquiyauyo","Paca","Paccha","Pancan","Parco","Pomacancha","Ricran","San Lorenzo","San Pedro de Chunan","Sausa","Sincos","Tunan Marca","Yauli","Yauyos");
            case "Junín":
                return Arrays.asList("Junin","Carhuamayo","Ondores","Ulcumayo");
            case "Satipo":
                return Arrays.asList("Satipo","Coviriali","Llaylla","Mazamari","Pampa Hermosa","Pangoa","Río Negro","Río Tambo","Vizcatan del Ene");
            case "Tarma":
                return Arrays.asList("Tarma","Acobamba","Huaricolca","Huasahuasi","La Unión","Palca","Palcamayo","San Pedro de Cajas","Tapo");
            case "Yauli":
                return Arrays.asList("La Oroya","Chacapalpa","Huay-Huay","Marcapomacocha","Morococha","Paccha","Santa Bárbara de Carhuacayan","Santa Rosa de Sacco","Suitucancha","Yauli");
            case "Trujillo":
                return Arrays.asList("Trujillo","El Porvenir","Florencia de Mora","Huanchaco","La Esperanza","Laredo","Moche","Poroto","Salaverry","Simbal","Victor Larco Herrera");
            case "Ascope":
                return Arrays.asList("Ascope","Chicama","Chocope","Magdalena de Cao","Paijan","Rázuri","Santiago de Cao","Casa Grande");
            case "Bolívar":
                return Arrays.asList("Bolívar","Bambamarca","Condormarca","Longotea","Uchumarca","Ucuncha");
            case "Chepén":
                return Arrays.asList("Chepen","Pacanga","Pueblo Nuevo");
            case "Julcán":
                return Arrays.asList("Julcan","Calamarca","Carabamba","Huaso");
            case "Otuzco":
                return Arrays.asList("Otuzco","Agallpampa","Charat","Huaranchal","La Cuesta","Mache","Paranday","Salpo","Sinsicap","Usquil");
            case "Gran Chimú":
                return Arrays.asList("Cascas","Lucma","Marmot","Sayapullo");
            case "Pacasmayo":
                return Arrays.asList("San Pedro de Lloc","Guadalupe","Jequetepeque","Pacasmayo","San José");
            case "Pataz":
                return Arrays.asList("Tayabamba","Buldibuyo","Chillia","Huancaspata","Huaylillas","Huayo","Ongon","Parcoy","Pataz","Pias","Santiago de Challas","Taurija","Urpay");
            case "Sánchez Carrión":
                return Arrays.asList("Huamachuco","Chugay","Cochorco","Curgos","Marcabal","Sanagoran","Sarin","Sartimbamba");
            case "Santiago de Chuco":
                return Arrays.asList("Santiago de Chuco","Angasmarca","Cachicadan","Mollebamba","Mollepata","Quiruvilca","Santa Cruz de Chuca","Sitabamba");
            case "Virú":
                return Arrays.asList("Viru","Chao","Guadalupito");
            case "Chiclayo":
                return Arrays.asList("Chiclayo","Chongoyape","Eten","Eten Puerto","José Leonardo Ortiz","La Victoria","Lagunas","Monsefu","Nueva Arica","Oyotun","Picsi","Pimentel","Reque","Santa Rosa","Saña","Cayalti","Patapo","Pomalca","Pucala","Tuman");
            case "Ferreñafe":
                return Arrays.asList("Ferreñafe","Cañaris","Incahuasi","Manuel Antonio Mesones Muro","Pitipo","Pueblo Nuevo");
            case "Lambayeque":
                return Arrays.asList("Lambayeque","Chochope","Illimo","Jayanca","Mochumi","Morrope","Motupe","Olmos","Pacora","Salas","San José","Tucume");
            case "Barranca":
                return Arrays.asList("Barranca","Paramonga","Pativilca","Supe","Supe Puerto");
            case "Cajatambo":
                return Arrays.asList("Cajatambo","Copa","Gorgor","Huancapon","Manas");
            case "Canta":
                return Arrays.asList("Canta","Arahuay","Huamantanga","Huaros","Lachaqui","San Buenaventura","Santa Rosa de Quives");
            case "Cañete":
                return Arrays.asList("San Vicente de Cañete","Asia","Calango","Cerro Azul","Chilca","Coayllo","Imperial","Lunahuana","Mala","Nuevo Imperial","Pacaran","Quilmana","San Antonio","San Luis","Santa Cruz de Flores","Zúñiga");
            case "Huaral":
                return Arrays.asList("Huaral","Atavillos Alto","Atavillos Bajo","Aucallama","Chancay","Ihuari","Lampian","Pacaraos","San Miguel de Acos","Santa Cruz de Andamarca","Sumbilca","Veintisiete de Noviembre");
            case "Huarochirí":
                return Arrays.asList("Matucana","Antioquia","Callahuanca","Carampoma	","Chicla","Cuenca","Huachupampa","Huanza","Huarochiri","Lahuaytambo","Langa","Laraos","Mariatana","Ricardo Palma","San Andrés de Tupicocha","San Antonio","San Bartolomé","San Damian","San Juan de Iris","San Juan de Tantaranche","San Lorenzo de Quinti","San Mateo","San Mateo de Otao","San Pedro de Casta","San Pedro de Huancayre","Sangallaya","Santa Cruz de Cocachacra","Santa Eulalia","Santiago de Anchucaya","Santiago de Tuna","Santo Domingo de Los Olleros","Surco");
            case "Huaura":
                return Arrays.asList("Huacho","Ambar","Caleta de Carquin","Checras","Hualmay","Huaura","Leoncio Prado","Paccho","Santa Leonor","Santa María","Sayan","Vegueta");
            case "Lima":
                return Arrays.asList("Lima","Ancón","Ate","Barranco","Breña","Carabayllo","Chaclacayo","Chorrillos","Cieneguilla","Comas","El Agustino","Independencia","Jesús María","La Molina","La Victoria","Lince","Los Olivos","Lurigancho","Lurin","Magdalena del Mar","Pueblo Libre","Miraflores","Pachacamac","Pucusana","Puente Piedra","Punta Hermosa","Punta Negra","Rímac","San Bartolo","San Borja","San Isidro","San Juan de Lurigancho","San Juan de Miraflores","San Luis","San Martín de Porres","San Miguel","Santa Anita","Santa María del Mar","Santa Rosa","Santiago de Surco","Surquillo","Villa El Salvador","Villa María del Triunfo");
            case "Oyón":
                return Arrays.asList("Oyon","Andajes","Caujul","Cochamarca","Navan","Pachangara");
            case "Yauyos":
                return Arrays.asList("Yauyos","Alis","Allauca","Ayaviri","Azángaro","Cacra","Carania","Catahuasi","Chocos","Cochas","Colonia","Hongos","Huampara","Huancaya","Huangascar","Huantan","Huañec","Laraos","Lincha","Madean","Miraflores","Omas","Putinza","Quinches","Quinocay","San Joaquín","San Pedro de Pilas","Tanta","Tauripampa","Tomas","Tupe","Viñac","Vitis");
            case "Maynas":
                return Arrays.asList("Iquitos","Alto Nanay","Fernando Lores","Indiana","Las Amazonas","Mazan","Napo","Punchana","Torres Causana","Belén","San Juan Bautista");
            case "Putumayo":
                return Arrays.asList("Putumayo","Rosa Panduro","Teniente Manuel Clavero","Yaguas");
            case "Alto Amazonas":
                return Arrays.asList("Yurimaguas","Balsapuerto","Jeberos","Lagunas","Santa Cruz","Teniente Cesar López Rojas");
            case "Loreto":
                return Arrays.asList("Nauta","Parinari","Tigre","Trompeteros","Urarinas");
            case "Mariscal Ramón Castilla":
                return Arrays.asList("Ramón Castilla","Pebas","Yavari","San Pablo");
            case "Requena":
                return Arrays.asList("Requena","Alto Tapiche","Capelo","Emilio San Martín","Maquia","Puinahua","Saquena","Soplin","Tapiche","Jenaro Herrera","Yaquerana");
            case "Ucayali":
                return Arrays.asList("Contamana","Inahuaya","Padre Márquez","Pampa Hermosa","Sarayacu","Vargas Guerra");
            case "Datem del Marañón":
                return Arrays.asList("Barranca","Cahuapanas","Manseriche","Morona","Pastaza","Andoas");
            //"Tambopata", "Manu", "Tahuamanu
            case "Tambopata":
                return Arrays.asList("Tambopata","Inambari","Las Piedras","Laberinto");
            case "Manu":
                return Arrays.asList("Manu","Fitzcarrald","Madre de Dios","Huepetuhe");
            case "Tahuamanu":
                return Arrays.asList("Iñapari","Iberia","Tahuamanu");
            //"Mariscal Nieto", "General Sánchez Cerro", "Ilo
            case "Mariscal Nieto":
                return Arrays.asList("Moquegua","Carumas","Cuchumbaya","Samegua","San Cristóbal","Torata");
            case "General Sánchez Cerro":
                return Arrays.asList("Omate","Chojata","Coalaque","Ichuña","La Capilla","Lloque","Matalaque","Puquina","Quinistaquillas","Ubinas","Yunga");
            case "Ilo":
                return Arrays.asList("Ilo","El Algarrobal","Pacocha");
            //"Pasco", "Oxapampa", "Daniel A. Carrión
            case "Pasco":
                return Arrays.asList("Chaupimarca","Huachon","Huariaca","Huayllay","Ninacaca","Pallanchacra","Paucartambo","San Francisco de Asís de Yarusyacan","Simon Bolívar","Ticlacayan","Tinyahuarco","Vicco","Yanacancha");
            case "Oxapampa":
                return Arrays.asList("Oxapampa","Chontabamba","Huancabamba","Palcazu","Pozuzo","Puerto Bermúdez","Villa Rica","Constitución");
            case "Daniel A. Carrión":
                return Arrays.asList("Yanahuanca","Chacayan","Goyllarisquizga","Paucar","San Pedro de Pillao","Santa Ana de Tusi","Tapuc","Vilcabamba");
            //"Ayabaca", "Huancabamba", "Morropón", "Piura", "Sechura", "Sullana", "Paita", "Talara
            case "Ayabaca":
                return Arrays.asList("Ayabaca","Frias","Jilili","Lagunas","Montero","Pacaipampa","Paimas","Sapillica","Sicchez","Suyo");
            case "Huancabamba":
                return Arrays.asList("Huancabamba","Canchaque","El Carmen de la Frontera","Huarmaca","Lalaquiz","San Miguel de El Faique","Sondor","Sondorillo");
            case "Morropón":
                return Arrays.asList("Chulucanas","Buenos Aires","Chalaco","La Matanza","Morropon","Salitral","San Juan de Bigote","Santa Catalina de Mossa","Santo Domingo","Yamango");
            case "Piura":
                return Arrays.asList("Piura","Castilla","Catacaos","Cura Mori","El Tallan","La Arena","La Unión","Las Lomas","Tambo Grande","Veintiseis de Octubre");
            case "Sechura":
                return Arrays.asList("Sechura","Bellavista de la Unión","Bernal","Cristo Nos Valga","Vice","Rinconada Llicuar");
            case "Sullana":
                return Arrays.asList("Sullana","Bellavista","Ignacio Escudero","Lancones","Marcavelica","Miguel Checa","Querecotillo","Salitral");
            case "Paita":
                return Arrays.asList("Paita","Amotape","Arenal","Colan","La Huaca","Tamarindo","Vichayal");
            case "Talara":
                return Arrays.asList("Pariñas","El Alto","La Brea","Lobitos","Los Organos","Mancora");
            //"San Román", "Puno", "Azángaro", "Chucuito", "El Collao", "Melgar", "Carabaya", "Huancané", "Sandia", "San Antonio de Putina", "Lampa", "Yunguyo", "Moho
            case "San Román":
                return Arrays.asList("Juliaca","Cabana","Cabanillas","Caracoto","San Miguel");
            case "Puno":
                return Arrays.asList("Puno","Acora","Amantani","Atuncolla","Capachica","Chucuito","Coata","Huata","Mañazo","Paucarcolla","Pichacani","Plateria","San Antonio","Tiquillaca","Vilque");
            case "Azángaro":
                return Arrays.asList("Azángaro","Achaya","Arapa","Asillo","Caminaca","Chupa","José Domingo Choquehuanca","Muñani","Potoni","Saman","San Anton","San José","San Juan de Salinas","Santiago de Pupuja","Tirapata");
            case "Chucuito":
                return Arrays.asList("Juli","Desaguadero","Huacullani","Kelluyo","Pisacoma","Pomata","Zepita");
            case "El Collao":
                return Arrays.asList("Ilave","Capazo","Pilcuyo","Santa Rosa","Conduriri");
            case "Melgar":
                return Arrays.asList("Ayaviri","Antauta","Cupi","Llalli","Macari","Nuñoa","Orurillo","Santa Rosa","Umachiri");
            case "Carabaya":
                return Arrays.asList("Macusani","Ajoyani","Ayapata","Coasa","Corani","Crucero","Ituata","Ollachea","San Gaban","Usicayos");
            case "Huancané":
                return Arrays.asList("Huancane","Cojata","Huatasani","Inchupalla","Pusi","Rosaspata","Taraco","Vilque Chico");
            case "Sandia":
                return Arrays.asList("Sandia","Cuyocuyo","Limbani","Patambuco","Phara","Quiaca","San Juan del Oro","Yanahuaya","Alto Inambari","San Pedro de Putina Punco");
            case "San Antonio de Putina":
                return Arrays.asList("Putina","Ananea","Pedro Vilca Apaza","Quilcapuncu","Sina");
            case "Lampa":
                return Arrays.asList("Lampa","Cabanilla","Calapuja","Nicasio","Ocuviri","Palca","Paratia","Pucara","Santa Lucia","Vilavila");
            case "Yunguyo":
                return Arrays.asList("Yunguyo", "Anapia", "Copani", "Cuturapi", "Ollaraya", "Tinicachi", "Unicachi");
            case "Moho":
                return Arrays.asList("Moho","Conima","Huayrapata","Tilali");
            //"Bellavista", "El Dorado", "Huallaga", "Lamas", "Mariscal Cáceres", "Moyobamba", "Picota", "Rioja", "San Martín", "Tocache
            case "Bellavista":
                return Arrays.asList("Bellavista","Alto Biavo","Bajo Biavo","Huallaga","San Pablo","San Rafael");
            case "El Dorado":
                return Arrays.asList("San José de Sisa","Agua Blanca","San Martín","Santa Rosa","Shatoja");
            case "Huallaga":
                return Arrays.asList("Saposoa","Alto Saposoa","El Eslabón","Piscoyacu","Sacanche","Tingo de Saposoa");
            case "Lamas":
                return Arrays.asList("Lamas","Alonso de Alvarado","Barranquita","Caynarachi","Cuñumbuqui","Pinto Recodo","Rumisapa","San Roque de Cumbaza","Shanao","Tabalosos","Zapatero");
            case "Mariscal Cáceres":
                return Arrays.asList("Juanjuí","Campanilla","Huicungo","Pachiza","Pajarillo");
            case "Moyobamba":
                return Arrays.asList("Moyobamba","Calzada","Habana","Jepelacio","Soritor","Yantalo");
            case "Picota":
                return Arrays.asList("Picota","Buenos Aires","Caspisapa","Pilluana","Pucacaca","San Cristóbal","San Hilarión","Shamboyacu","Tingo de Ponasa","Tres Unidos");
            case "Rioja":
                return Arrays.asList("Rioja","Awajun","Elías Soplin Vargas","Nueva Cajamarca","Pardo Miguel","Posic","San Fernando","Yorongos","Yuracyacu");
            case "San Martín":
                return Arrays.asList("Tarapoto","Alberto Leveau","Cacatachi","Chazuta","Chipurana","El Porvenir","Huimbayoc","Juan Guerra","La Banda de Shilcayo","Morales","Papaplaya","San Antonio","Sauce","Shapaja");
            case "Tocache":
                return Arrays.asList("Tocache","Nuevo Progreso","Polvora","Shunte","Uchiza");
            //"Tacna", "Candarave", "Jorge Basadre", "Tarata
            case "Tacna":
                return Arrays.asList("Tacna","Alto de la Alianza","Calana","Ciudad Nueva","Inclan","Pachia","Palca","Pocollay","Sama","Coronel Gregorio Albarracín Lanchipa","La Yarada los Palos");
            case "Candarave":
                return Arrays.asList("Candarave","Cairani","Camilaca","Curibaya","Huanuara","Quilahuani");
            case "Jorge Basadre":
                return Arrays.asList("Locumba","Ilabaya","Ite");
            case "Tarata":
                return Arrays.asList("Tarata","Héroes Albarracín","Estique","Estique-Pampa","Sitajara","Susapaya","Tarucachi","Ticaco");
            //"Tumbes", "Zarumilla", "Contralmirante Villar
            case "Tumbes":
                return Arrays.asList("Tumbes","Corrales","La Cruz","Pampas de Hospital","San Jacinto","San Juan de la Virgen");
            case "Zarumilla":
                return Arrays.asList("Zarumilla","Aguas Verdes","Matapalo","Papayal");
            case "Contralmirante Villar":
                return Arrays.asList("Zorritos","Casitas","Canoas de Punta Sal");
            //"Coronel Portillo", "Atalaya", "Padre Abad", "Purús
            case "Coronel Portillo":
                return Arrays.asList("Calleria","Campoverde","Iparia","Masisea","Yarinacocha","Nueva Requena","Manantay");
            case "Atalaya":
                return Arrays.asList("Raymondi","Sepahua","Tahuania","Yurua");
            case "Padre Abad":
                return Arrays.asList("Padre Abad","Irazola","Curimana","Neshuya","Alexander Von Humboldt");
            case "Purús":
                return Arrays.asList("Purus");
        }
        return Collections.singletonList("");
    }

}
