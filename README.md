# Palwiz

Palwiz on opettajille ja oppilaille suunniteltu web-sovellus, jossa opiskelijat voivat antaa palautetta oppitunneista. Palautetta annetaan valitsemalla kolmesta väristä sopiva vaihtoehto. Punainen kuvastaa huonoa kokemusta, keltainen kuvastaa keskivertoa ja vihreä kuvastaa hyvää kokemusta. Opettaja näkee oppilaiden vastaukset anonyymeinä. Oppilas antaa sovellukseen kurssin koodin, jonka jälkeen hän pääsee antamaan palautetta. Palautteen antaminen on avoinna vain kurssille määriteltyinä ajankohtina.

Opettaja pystyy luomaan itselleen tunnukset sovellukseen. Sovellukseen kirjautuneena pystyy lisäämään uusia kursseja ja tarkastelemaan saatuja palautteita kurssikohtaisesti. Palautteet tallennetaan tietokantaan. Palautteiden avulla opettajan on mahdollista kehittää oppituntejaan paremmiksi.

Projekti on Java-ohjelmointikielellä toteutettu Vaadin sovellus, joka hyödyntää Spring Bootia.

Sovelluksen ovat kehittäneet Atte Asikainen, Juho Ahola, Eveliina Heino ja Sebastian Wolf.

## Sovelluksen käyttöönotto

Sovellus on toteutettu Maven projektina. Voit kloonata projektin GitHubista omalle koneellesi ja sen jälkeen avata sen mieleisessäsi IDE:ssä tavallisen Maven projektin tapaan. Vaadin tukee muun muassa Eclipseä, IntelliJ IDEA, NetBeansia ja VS Codea. [Tarkemmat ohjeet löytyvät täältä.](https://vaadin.com/docs/latest/guide/step-by-step/importing). Koneella tulee olla asennettuna Node.js. Noden voi ladata osoitteesta [nodejs.org](https://nodejs.org/en/). Tarvittaessa aja komento `npm init` sovelluksen juurihakemistossa. Sovelluksessa käytetään Java 11:sta.

Sovelluksen tietokanta sijaitsee Metropolian Educloud-palvelimella virtuaalikoneella. Virtuaalikone on tilattu Juhon nimellä ja sen käyttöaika loppuu toko-/kesäkuussa 2023. Tietokannan käyttöä varten käyttäjä tarvitsee koneelleen tiedoston, jossa on tarvittavat tiedot tietokantayhteyden muodostamiseen. Oikea polku tiedoston sijaintiin tulee käydä muokkaamassa `application.properties` -tiedostoon, kohtaan `spring.config.import="optional:file:/Users/omaPolku/db.properties"`. Application.properties -tiedosto löytty kansiosta: src\main\resources. Jos sovellusta käytetään Metropolian verkon ulkopuolella, tulee käyttää Metropolian VPN-yhteyttä.

Sovellus käynnistetään `Application.java`-tiedostosta. Tiedosto löytyy polulta: src\main\java\com\example\application\Application.java.

## Projektin rakenne

- `MainLayout.java` tiedosto sijaitsee kansiossa `src/main/java`. Se sisältää sovelluksen navigointi palkin asettelun. Näkymässä käytetään Vaadinin
  [App Layoutia](https://vaadin.com/docs/components/app-layout).
- `views` pakkaus, joka sijaitsee `src/main/java` -kansiossa, sisältää sovelluksen eri näkymät.
- `themes` kansio `frontend/` kansiossa sisältää itse määritetyt CSS-tiedostot.

## Projektin kehitysympäristö

Sovellus on luotu käyttämällä Vaadin Flow full-stack sovelluskehystä. Vaadinia käyttäessä sekä frontend että backend voidaan kirjoittaa Javalla. Vaadin Flow -sovellukset renderöidään selaimessa starndardi HTML:ksi. Sovellukset toimivat suoraan kaikissa moderneissa selaimissa ja laitteissa. Vaadinista löytyy paljon valmiita UI komponentteja, mutta on mahdollista myös muokata ja luoda omia komponentteja. Palwiz-sovelluksessa on hyödynnetty Vaadinin valmiita komponentteja sekä luotu omia. Sovelluksen pohja on luotu hyödyntämällä [Vaadin Startia](https://start.vaadin.com/). Vaadinin Core -versio on ilmainen open-source platform. Palwiz -projektissa on hyödynnetty myös Pro-version ominaisuuksia. Pro-version saa ilmaiseksi käyttöön opiskelijana.

Sovelluksessa käytetään Spring Bootia, joka sisältää sulautetun web-palvelimen. Se mahdollistaa sovelluksen suorittamisen suoraan Java-sovelluksena. Sping Bootin vaatimat määrittelyt `pom.xml` -tiedostoa varten löytyvät osoitteesta [vaadin.com](https://vaadin.com/docs/latest/integrations/spring/spring-boot). Sovellus käynnistetään `Application.java`-tiedostosta, josta löytyy Spring Bootin vaatima `@SpringBootApplication`-annotaatio.

Sovelluksessa hyödynnetään MariaDB-tietokantaa, joka on käynnissä palvelinkoneella Metropolian Educloud-pilvipalvelimella. Omaan tietokantaan yhdistäminen tapahtuu tekemällä tarvittavat muutokset `application.properties`-tiedostoon. Tarkemmat ohjeet löytyvät [täältä](https://vaadin.com/docs/latest/integrations/databases/mysql).

## Tuotantoversion luominen

Luodaksesi tuotantoversion suorita `mvnw clean package -Pproduction` (Windows),
tai `./mvnw clean package -Pproduction` (Mac & Linux). Komento luo
JAR tiedoston, joka sisältää kaikki tarvittavat asiat tuotantoon viemiseksi. JAR-tiedosto löytyy `target`-kansiosta, kun se on valmis.

Voit käynnistää JAR-tiedoston komennolla:
`java -jar target/myapp-1.0-SNAPSHOT.jar`

## Sovelluksen testaus

Sovellusta on testattu monin tavoin koko prosessin ajan. Testien tekemiseen on käytetty [JUnit5:sta](https://junit.org/junit5/) ja [Mockitoa](https://site.mockito.org/). Testit on ajettu Jenkinsissä, jossa on seurattu testien läpimenoa ja testien trendiä. Testikattavuusraportti on generoitu säännöllisesti Eclipsessä. Testikattavuuden generoimisessa hyödynnetään Mavenin [Surefire-pluginia](https://maven.apache.org/surefire/maven-surefire-report-plugin/).

## Sovelluksen tulevaisuus

Palwiz on erinomainen ja tehokas sovellus, jolla on valoisa tulevaisuus. Palwizia kehitetään jatkuvasti kerättyjen käyttökokemusten pohjalta ja sovelluksen kansainvälisyyttä kehitetään lisäämällä uusia kieliä. Tällä hetkellä sovellus on käytettävissä sekä suomeksi että englanniksi. Sovellus on heti käytettävissä osoitteessa Palwiz.com, kunhan sopiva sponsori löydetään tukemaan sovelluksen ylläpitoa.

## Hyödyllisiä linkkejä

- Lue Vaadinin dokumentaatiota [vaadin.com/docs](https://vaadin.com/docs).
- Lue tutoriaaleja [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Katso videoita [vaadin.com/learn/training](https://vaadin.com/learn/training).
