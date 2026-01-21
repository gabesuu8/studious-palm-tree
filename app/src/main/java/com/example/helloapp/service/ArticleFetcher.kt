package com.example.helloapp.service

import com.example.helloapp.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleFetcher {
    
    suspend fun fetchHealthcareArticles(languageCode: String = "en"): List<Article> = withContext(Dispatchers.IO) {
        when (languageCode) {
            "fr" -> getFrenchArticles()
            else -> getEnglishArticles()
        }
    }
    
    private fun getEnglishArticles(): List<Article> {
        return listOf(
            // MALARIA - English
            Article(
                title = "Malaria",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Globally in 2024, there were an estimated 282 million malaria cases and 610,000 malaria deaths.</li>
        <li>The WHO African Region carries 95% of the global malaria burden.</li>
        <li>Children under 5 years of age account for about 75% of all malaria deaths in Africa.</li>
        <li>Malaria is preventable and curable with early diagnosis and treatment.</li>
        <li>Insecticide-treated bed nets and antimalarial medicines are the most effective prevention tools.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Malaria is a life-threatening disease spread to humans by some types of mosquitoes. It is mostly found in tropical countries. It is preventable and curable.</p>
<p>The infection is caused by a parasite and does not spread from person to person. Symptoms can be mild or life-threatening. Mild symptoms are fever, chills and headache. Severe symptoms include fatigue, confusion, seizures, and difficulty breathing.</p>
<p>Infants, children under 5 years, pregnant women and girls, travellers and people with HIV or AIDS are at higher risk of severe infection.</p>

<hr/>

<h2>Symptoms</h2>
<p>The most common early symptoms of malaria are fever, headache and chills. Symptoms usually start within 10–15 days of getting bitten by an infected mosquito.</p>

<h3>Severe symptoms include:</h3>
<ul>
    <li>Extreme tiredness and fatigue</li>
    <li>Impaired consciousness</li>
    <li>Multiple convulsions</li>
    <li>Difficulty breathing</li>
    <li>Dark or bloody urine</li>
    <li>Jaundice (yellowing of the eyes and skin)</li>
    <li>Abnormal bleeding</li>
</ul>

<p class="warning"><b>⚠️ People with severe symptoms should get emergency care right away.</b></p>

<hr/>

<h2>Prevention</h2>
<p>Malaria can be prevented by avoiding mosquito bites and by taking medicines.</p>

<h3>Lower the risk by avoiding mosquito bites:</h3>
<ul>
    <li>Use mosquito nets when sleeping in places where malaria is present</li>
    <li>Use mosquito repellents (containing DEET, IR3535 or Icaridin) after dusk</li>
    <li>Use coils and vaporizers</li>
    <li>Wear protective clothing (long sleeves and pants)</li>
    <li>Use window screens</li>
</ul>

<h3>Vaccines</h3>
<p>Since October 2021, WHO has recommended broad use of the RTS,S/AS01 malaria vaccine among children living in regions with moderate to high malaria transmission. In October 2023, WHO recommended a second safe and effective malaria vaccine, R21/Matrix-M.</p>

<hr/>

<h2>Treatment</h2>
<p>Early diagnosis and treatment of malaria reduces disease, prevents deaths and contributes to reducing transmission.</p>

<h3>Common medicines for malaria:</h3>
<ul>
    <li><b>Artemisinin-based combination therapy (ACT)</b> – the most effective treatment</li>
    <li><b>Chloroquine</b> – for P. vivax infection where effective</li>
    <li><b>Primaquine</b> – to prevent relapses</li>
</ul>

<p><b>Important:</b> Complete the full course of treatment even if you feel better.</p>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Eat iron-rich foods (dark leafy greens, beans, meat) to prevent anemia</li>
    <li>Include vitamin C foods (oranges, tomatoes) to boost immunity</li>
    <li>Stay well-hydrated with clean, safe water</li>
    <li>Get adequate rest (7-8 hours of sleep)</li>
    <li>Exercise regularly when healthy to strengthen your immune system</li>
    <li>Avoid outdoor activities at dusk and dawn when mosquitoes are most active</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/malaria">www.who.int/news-room/fact-sheets/detail/malaria</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/malaria">www.cdc.gov/malaria</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on malaria: 282 million cases globally in 2024. Learn about symptoms, prevention with bed nets and vaccines, and treatment.",
                source = "WHO, CDC",
                category = "Infectious Diseases"
            ),
            
            // DIARRHEA - English
            Article(
                title = "Diarrhoeal disease",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Diarrhoeal disease is the second leading cause of death in children under 5 years old.</li>
        <li>Each year, diarrhoea kills around 443,000 children under 5.</li>
        <li>Diarrhoea can be prevented by safe drinking water, adequate sanitation, and hand washing with soap.</li>
        <li>Diarrhoea is treated with oral rehydration solution (ORS) and zinc supplements.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Diarrhoea is the passage of 3 or more loose or liquid stools per day. It is usually a symptom of gastrointestinal infection caused by bacteria, viruses or parasites.</p>

<hr/>

<h2>Signs of dehydration</h2>
<h3>Mild to moderate:</h3>
<ul>
    <li>Thirst</li>
    <li>Dry mouth and lips</li>
    <li>Decreased urination</li>
</ul>

<h3 class="warning">Severe dehydration (EMERGENCY):</h3>
<ul>
    <li>Very sunken eyes</li>
    <li>Unable to drink</li>
    <li>Lethargy or unconsciousness</li>
</ul>

<hr/>

<h2>Treatment</h2>
<h3>Oral Rehydration Therapy (ORS)</h3>
<div class="highlight-box">
    <b>🏠 How to make ORS at home:</b><br/>
    Mix in 1 litre of clean water:<br/>
    • 6 level teaspoons of sugar<br/>
    • ½ level teaspoon of salt
</div>

<hr/>

<h2>Prevention</h2>
<ul>
    <li>Boil water before drinking</li>
    <li>Wash hands with soap before eating and after toilet</li>
    <li>Cook food thoroughly</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/diarrhoeal-disease">www.who.int/news-room/fact-sheets/detail/diarrhoeal-disease</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on diarrhoeal disease: second leading cause of death in children under 5. Learn about ORS treatment and prevention.",
                source = "WHO, UNICEF",
                category = "Child Health"
            ),
            
            // CHOLERA - English
            Article(
                title = "Cholera",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Cholera is an acute diarrhoeal infection caused by contaminated food or water.</li>
        <li>Cholera can kill within hours if left untreated.</li>
        <li>Up to 80% of cases can be treated with oral rehydration salts.</li>
        <li>Safe water and sanitation are the most effective prevention.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Cholera is an extremely virulent disease that can cause severe acute watery diarrhoea. It takes between 12 hours and 5 days for symptoms to appear.</p>

<hr/>

<h2>Symptoms</h2>
<h3 class="warning">Severe cholera (EMERGENCY):</h3>
<ul>
    <li>Profuse watery diarrhoea ("rice water" stools)</li>
    <li>Vomiting</li>
    <li>Leg cramps</li>
    <li>Rapid dehydration</li>
</ul>

<p class="warning"><b>⚠️ Severe cholera can cause death within hours if not treated!</b></p>

<hr/>

<h2>Treatment</h2>
<p>Start ORS immediately. Give as much as the person can drink. Severe cases need IV fluids.</p>

<hr/>

<h2>Prevention</h2>
<ul>
    <li>Boil water for at least 1 minute</li>
    <li>Cook food thoroughly, especially seafood</li>
    <li>Wash hands frequently with soap</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/cholera">www.who.int/news-room/fact-sheets/detail/cholera</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on cholera: acute diarrhoeal infection that can kill within hours. Learn about ORS treatment and safe water prevention.",
                source = "WHO, GTFCC",
                category = "Infectious Diseases"
            ),
            
            // TYPHOID - English
            Article(
                title = "Typhoid",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Typhoid fever is caused by Salmonella typhi bacteria.</li>
        <li>11-20 million people get sick from typhoid each year.</li>
        <li>Typhoid spreads through contaminated food and water.</li>
        <li>Two vaccines are available to prevent typhoid.</li>
    </ul>
</div>

<h2>Symptoms</h2>
<ul>
    <li>Gradually increasing fever</li>
    <li>Headache and weakness</li>
    <li>Abdominal pain</li>
    <li>Constipation or diarrhoea</li>
</ul>

<hr/>

<h2>Treatment</h2>
<p>Typhoid is treated with antibiotics. Complete the full course (7-14 days).</p>

<hr/>

<h2>Prevention</h2>
<ul>
    <li>Boil or treat all drinking water</li>
    <li>Eat thoroughly cooked foods</li>
    <li>Get vaccinated</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/typhoid">www.who.int/news-room/fact-sheets/detail/typhoid</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on typhoid: 11-20 million cases annually. Learn about symptoms, antibiotic treatment, and prevention.",
                source = "WHO, CDC",
                category = "Infectious Diseases"
            ),
            
            // HIV/AIDS - English
            Article(
                title = "HIV/AIDS",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>HIV attacks the body's immune system.</li>
        <li>Approximately 39 million people are living with HIV globally.</li>
        <li>HIV can be suppressed with antiretroviral therapy (ART).</li>
        <li>With proper treatment, people with HIV can live long, healthy lives.</li>
    </ul>
</div>

<h2>Transmission</h2>
<h3>HIV IS spread through:</h3>
<ul>
    <li>Unprotected sexual contact</li>
    <li>Sharing needles</li>
    <li>Mother-to-child during pregnancy/birth/breastfeeding</li>
</ul>

<h3>HIV is NOT spread through:</h3>
<ul>
    <li>Hugging, shaking hands</li>
    <li>Sharing food or utensils</li>
    <li>Mosquito bites</li>
</ul>

<hr/>

<h2>Testing & Treatment</h2>
<p>HIV testing is free, confidential, and quick. ART is available free in most African countries.</p>
<p><b>Undetectable = Untransmittable (U=U)</b></p>

<hr/>

<h2>Prevention</h2>
<ul>
    <li>Use condoms correctly every time</li>
    <li>Get tested regularly</li>
    <li>PrEP: Daily pill for high-risk individuals</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">UNAIDS</div>
        <a href="https://www.unaids.org">www.unaids.org</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on HIV/AIDS: 39 million people living with HIV globally. Learn about testing, ART treatment, and prevention.",
                source = "WHO, UNAIDS",
                category = "Sexual Health"
            ),
            
            // MATERNAL HEALTH - English
            Article(
                title = "Maternal health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>800 women die daily from preventable pregnancy-related causes.</li>
        <li>94% of maternal deaths occur in low-income countries.</li>
        <li>WHO recommends 8 antenatal care contacts during pregnancy.</li>
    </ul>
</div>

<h2>Antenatal care</h2>
<ul>
    <li>Blood pressure and weight monitoring</li>
    <li>Baby's growth and heartbeat checks</li>
    <li>Nutrition counselling</li>
</ul>

<hr/>

<h2 class="warning">⚠️ Danger signs - Seek immediate care:</h2>
<ul>
    <li>Vaginal bleeding</li>
    <li>Severe headache or blurred vision</li>
    <li>High fever</li>
    <li>Convulsions</li>
</ul>

<hr/>

<h2>Breastfeeding</h2>
<ul>
    <li>Start within 1 hour of birth</li>
    <li>Exclusive breastfeeding for 6 months</li>
    <li>Continue up to 2 years</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/health-topics/maternal-health">www.who.int/health-topics/maternal-health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on maternal health: 800 women die daily from preventable causes. Learn about antenatal care and danger signs.",
                source = "WHO, UNICEF",
                category = "Maternal Health"
            ),
            
            // NUTRITION - English
            Article(
                title = "Healthy diet",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>A healthy diet protects against malnutrition and disease.</li>
        <li>Unhealthy diet is a leading global health risk.</li>
        <li>Energy intake should balance energy expenditure.</li>
    </ul>
</div>

<h2>Food groups</h2>
<h3>1. Energy foods:</h3>
<p>Maize, rice, cassava, potatoes, bread</p>

<h3>2. Body-building foods:</h3>
<p>Beans, fish, eggs, meat, milk</p>

<h3>3. Protective foods:</h3>
<p>Vegetables, fruits</p>

<hr/>

<h2>Healthy eating tips</h2>
<ul>
    <li>Eat 3 meals daily</li>
    <li>Eat fruits and vegetables every day</li>
    <li>Limit sugar, salt, and processed foods</li>
    <li>Drink 6-8 glasses of water daily</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/healthy-diet">www.who.int/news-room/fact-sheets/detail/healthy-diet</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on healthy diet: essential for preventing malnutrition and disease.",
                source = "WHO, FAO",
                category = "Nutrition"
            ),
            
            // WATER - English
            Article(
                title = "Drinking-water",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Contaminated water transmits diseases like cholera and typhoid.</li>
        <li>2 billion people use contaminated water sources.</li>
        <li>Safe water could prevent 400,000 deaths annually.</li>
    </ul>
</div>

<h2>Water treatment</h2>
<h3>1. Boiling:</h3>
<ul>
    <li>Boil for at least 1 minute</li>
    <li>Let cool naturally</li>
    <li>Store in clean container</li>
</ul>

<h3>2. Chlorine:</h3>
<p>Use purification tablets or 2 drops bleach per litre</p>

<hr/>

<h2>Hand hygiene</h2>
<p>Wash hands with soap:</p>
<ul>
    <li>Before eating</li>
    <li>After using toilet</li>
    <li>After changing diapers</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/drinking-water">www.who.int/news-room/fact-sheets/detail/drinking-water</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on drinking water: 2 billion people lack safe water. Learn about water treatment and hand hygiene.",
                source = "WHO, UNICEF",
                category = "Hygiene & Sanitation"
            ),
            
            // FIRST AID - English
            Article(
                title = "First aid",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>First aid is immediate care until professional help arrives.</li>
        <li>Basic skills can save lives.</li>
        <li>Goals: preserve life, prevent worsening, promote recovery.</li>
    </ul>
</div>

<h2>Bleeding</h2>
<ul>
    <li>Apply firm pressure with clean cloth</li>
    <li>Raise injured limb above heart</li>
    <li>Get emergency help for severe bleeding</li>
</ul>

<hr/>

<h2>Burns</h2>
<ul>
    <li>Cool with clean water for 10-20 minutes</li>
    <li>Do NOT apply butter or oil</li>
    <li>Cover with clean bandage</li>
</ul>

<hr/>

<h2>Choking</h2>
<p>If person cannot breathe: Stand behind, give quick upward thrusts above belly button.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Red Cross / Red Crescent</div>
        <a href="https://www.ifrc.org/first-aid">www.ifrc.org/first-aid</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Essential first aid guide: bleeding, burns, choking. Life-saving skills everyone should know.",
                source = "Red Cross, WHO",
                category = "Emergency Care"
            ),
            
            // MENTAL HEALTH - English
            Article(
                title = "Mental health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>1 in 8 people lives with a mental disorder.</li>
        <li>Mental health conditions are treatable.</li>
        <li>Mental health is as important as physical health.</li>
    </ul>
</div>

<h2>Depression signs:</h2>
<ul>
    <li>Persistent sadness</li>
    <li>Loss of interest</li>
    <li>Sleep changes</li>
    <li>Fatigue</li>
</ul>

<hr/>

<h2>When to seek help</h2>
<ul>
    <li>Symptoms lasting more than 2 weeks</li>
    <li>Difficulty with daily activities</li>
    <li>Thoughts of self-harm</li>
</ul>

<p class="warning"><b>⚠️ CRISIS: Thoughts of suicide = medical emergency. Seek immediate help.</b></p>

<hr/>

<h2>Healthy lifestyle</h2>
<ul>
    <li>Exercise 30 minutes daily</li>
    <li>Sleep 7-8 hours</li>
    <li>Connect with others</li>
    <li>Spend time in nature</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/mental-health">www.who.int/news-room/fact-sheets/detail/mental-health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on mental health: 1 in 8 people has a mental disorder. Learn about symptoms and support.",
                source = "WHO",
                category = "Mental Health"
            )
        )
    }
    
    private fun getFrenchArticles(): List<Article> {
        return listOf(
            // PALUDISME - French
            Article(
                title = "Paludisme",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>En 2024, on estime à 282 millions le nombre de cas de paludisme et à 610 000 le nombre de décès dans le monde.</li>
        <li>La Région africaine de l'OMS supporte 95% de la charge mondiale du paludisme.</li>
        <li>Les enfants de moins de 5 ans représentent environ 75% des décès dus au paludisme en Afrique.</li>
        <li>Le paludisme est évitable et guérissable grâce à un diagnostic et un traitement précoces.</li>
        <li>Les moustiquaires imprégnées d'insecticide et les médicaments antipaludiques sont les outils de prévention les plus efficaces.</li>
    </ul>
</div>

<h2>Aperçu</h2>
<p>Le paludisme est une maladie potentiellement mortelle transmise à l'homme par certains types de moustiques. On le trouve principalement dans les pays tropicaux. Il est évitable et guérissable.</p>
<p>L'infection est causée par un parasite et ne se transmet pas de personne à personne. Les symptômes peuvent être légers ou potentiellement mortels. Les symptômes légers sont la fièvre, les frissons et les maux de tête. Les symptômes graves comprennent la fatigue, la confusion, les convulsions et les difficultés respiratoires.</p>

<hr/>

<h2>Symptômes</h2>
<p>Les premiers symptômes les plus courants du paludisme sont la fièvre, les maux de tête et les frissons. Les symptômes apparaissent généralement dans les 10 à 15 jours suivant la piqûre d'un moustique infecté.</p>

<h3>Les symptômes graves comprennent :</h3>
<ul>
    <li>Fatigue et épuisement extrêmes</li>
    <li>Troubles de la conscience</li>
    <li>Convulsions multiples</li>
    <li>Difficultés respiratoires</li>
    <li>Urines foncées ou sanglantes</li>
    <li>Jaunisse (jaunissement des yeux et de la peau)</li>
    <li>Saignements anormaux</li>
</ul>

<p class="warning"><b>⚠️ Les personnes présentant des symptômes graves doivent recevoir des soins d'urgence immédiatement.</b></p>

<hr/>

<h2>Prévention</h2>
<p>Le paludisme peut être prévenu en évitant les piqûres de moustiques et en prenant des médicaments.</p>

<h3>Réduire le risque en évitant les piqûres de moustiques :</h3>
<ul>
    <li>Utiliser des moustiquaires pour dormir dans les zones où le paludisme est présent</li>
    <li>Utiliser des répulsifs anti-moustiques (contenant du DEET, IR3535 ou Icaridin) après le crépuscule</li>
    <li>Utiliser des serpentins et vaporisateurs</li>
    <li>Porter des vêtements protecteurs (manches longues et pantalons)</li>
    <li>Utiliser des moustiquaires aux fenêtres</li>
</ul>

<h3>Vaccins</h3>
<p>Depuis octobre 2021, l'OMS recommande l'utilisation généralisée du vaccin antipaludique RTS,S/AS01 chez les enfants vivant dans des régions à transmission modérée à élevée du paludisme.</p>

<hr/>

<h2>Traitement</h2>
<p>Le diagnostic et le traitement précoces du paludisme réduisent la maladie, préviennent les décès et contribuent à réduire la transmission.</p>

<h3>Médicaments courants contre le paludisme :</h3>
<ul>
    <li><b>Combinaisons thérapeutiques à base d'artémisinine (CTA)</b> – le traitement le plus efficace</li>
    <li><b>Chloroquine</b> – pour l'infection à P. vivax là où elle est efficace</li>
    <li><b>Primaquine</b> – pour prévenir les rechutes</li>
</ul>

<p><b>Important :</b> Terminez le traitement complet même si vous vous sentez mieux.</p>

<hr/>

<h2>Recommandations pour un mode de vie sain</h2>
<ul>
    <li>Mangez des aliments riches en fer (légumes verts foncés, haricots, viande) pour prévenir l'anémie</li>
    <li>Incluez des aliments riches en vitamine C (oranges, tomates) pour renforcer l'immunité</li>
    <li>Restez bien hydraté avec de l'eau propre et sûre</li>
    <li>Dormez suffisamment (7-8 heures de sommeil)</li>
    <li>Faites régulièrement de l'exercice lorsque vous êtes en bonne santé</li>
    <li>Évitez les activités extérieures au crépuscule et à l'aube</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/malaria">www.who.int/fr/news-room/fact-sheets/detail/malaria</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche d'information de l'OMS sur le paludisme : 282 millions de cas en 2024. Symptômes, prévention et traitement.",
                source = "OMS, CDC",
                category = "Maladies Infectieuses"
            ),
            
            // DIARRHÉE - French
            Article(
                title = "Maladies diarrhéiques",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Les maladies diarrhéiques sont la deuxième cause de mortalité chez les enfants de moins de 5 ans.</li>
        <li>Chaque année, la diarrhée tue environ 443 000 enfants de moins de 5 ans.</li>
        <li>La diarrhée peut être prévenue par l'eau potable, l'assainissement et le lavage des mains au savon.</li>
        <li>La diarrhée se traite avec une solution de réhydratation orale (SRO) et des suppléments de zinc.</li>
    </ul>
</div>

<h2>Aperçu</h2>
<p>La diarrhée est l'émission de 3 selles molles ou liquides ou plus par jour. C'est généralement un symptôme d'une infection gastro-intestinale causée par des bactéries, des virus ou des parasites.</p>

<hr/>

<h2>Signes de déshydratation</h2>
<h3>Légère à modérée :</h3>
<ul>
    <li>Soif</li>
    <li>Bouche et lèvres sèches</li>
    <li>Diminution de la miction</li>
</ul>

<h3 class="warning">Déshydratation sévère (URGENCE) :</h3>
<ul>
    <li>Yeux très enfoncés</li>
    <li>Incapacité de boire</li>
    <li>Léthargie ou inconscience</li>
</ul>

<hr/>

<h2>Traitement</h2>
<h3>Thérapie de réhydratation orale (SRO)</h3>
<div class="highlight-box">
    <b>🏠 Comment préparer la SRO à la maison :</b><br/>
    Mélanger dans 1 litre d'eau propre :<br/>
    • 6 cuillères à café rases de sucre<br/>
    • ½ cuillère à café rase de sel
</div>

<hr/>

<h2>Prévention</h2>
<ul>
    <li>Faire bouillir l'eau avant de boire</li>
    <li>Se laver les mains au savon avant de manger et après les toilettes</li>
    <li>Bien cuire les aliments</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/diarrhoeal-disease">www.who.int/fr/news-room/fact-sheets/detail/diarrhoeal-disease</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur les maladies diarrhéiques : deuxième cause de décès chez les enfants. Traitement par SRO et prévention.",
                source = "OMS, UNICEF",
                category = "Santé Infantile"
            ),
            
            // CHOLÉRA - French
            Article(
                title = "Choléra",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Le choléra est une infection diarrhéique aiguë causée par l'ingestion d'aliments ou d'eau contaminés.</li>
        <li>Le choléra peut tuer en quelques heures s'il n'est pas traité.</li>
        <li>Jusqu'à 80% des cas peuvent être traités avec des sels de réhydratation orale.</li>
        <li>L'eau potable et l'assainissement sont les moyens de prévention les plus efficaces.</li>
    </ul>
</div>

<h2>Aperçu</h2>
<p>Le choléra est une maladie extrêmement virulente qui peut provoquer une diarrhée aqueuse aiguë sévère. Il faut entre 12 heures et 5 jours pour que les symptômes apparaissent.</p>

<hr/>

<h2>Symptômes</h2>
<h3 class="warning">Choléra sévère (URGENCE) :</h3>
<ul>
    <li>Diarrhée aqueuse profuse (selles « eau de riz »)</li>
    <li>Vomissements</li>
    <li>Crampes dans les jambes</li>
    <li>Déshydratation rapide</li>
</ul>

<p class="warning"><b>⚠️ Le choléra sévère peut entraîner la mort en quelques heures s'il n'est pas traité !</b></p>

<hr/>

<h2>Traitement</h2>
<p>Commencer la SRO immédiatement. Donner autant que la personne peut boire. Les cas graves nécessitent des liquides IV.</p>

<hr/>

<h2>Prévention</h2>
<ul>
    <li>Faire bouillir l'eau pendant au moins 1 minute</li>
    <li>Bien cuire les aliments, surtout les fruits de mer</li>
    <li>Se laver fréquemment les mains au savon</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/cholera">www.who.int/fr/news-room/fact-sheets/detail/cholera</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur le choléra : infection diarrhéique aiguë pouvant tuer en quelques heures. Traitement par SRO et prévention.",
                source = "OMS, GTFCC",
                category = "Maladies Infectieuses"
            ),
            
            // FIÈVRE TYPHOÏDE - French
            Article(
                title = "Fièvre typhoïde",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La fièvre typhoïde est causée par la bactérie Salmonella typhi.</li>
        <li>11 à 20 millions de personnes contractent la typhoïde chaque année.</li>
        <li>La typhoïde se transmet par l'eau et les aliments contaminés.</li>
        <li>Deux vaccins sont disponibles pour prévenir la typhoïde.</li>
    </ul>
</div>

<h2>Symptômes</h2>
<ul>
    <li>Fièvre augmentant progressivement</li>
    <li>Maux de tête et faiblesse</li>
    <li>Douleurs abdominales</li>
    <li>Constipation ou diarrhée</li>
</ul>

<hr/>

<h2>Traitement</h2>
<p>La typhoïde est traitée par antibiotiques. Terminez le traitement complet (7-14 jours).</p>

<hr/>

<h2>Prévention</h2>
<ul>
    <li>Faire bouillir ou traiter toute l'eau potable</li>
    <li>Manger des aliments bien cuits</li>
    <li>Se faire vacciner</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/typhoid">www.who.int/fr/news-room/fact-sheets/detail/typhoid</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur la typhoïde : 11-20 millions de cas par an. Symptômes, traitement antibiotique et prévention.",
                source = "OMS, CDC",
                category = "Maladies Infectieuses"
            ),
            
            // VIH/SIDA - French
            Article(
                title = "VIH/SIDA",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Le VIH attaque le système immunitaire de l'organisme.</li>
        <li>Environ 39 millions de personnes vivent avec le VIH dans le monde.</li>
        <li>Le VIH peut être supprimé par un traitement antirétroviral (TAR).</li>
        <li>Avec un traitement approprié, les personnes vivant avec le VIH peuvent vivre longtemps et en bonne santé.</li>
    </ul>
</div>

<h2>Transmission</h2>
<h3>Le VIH SE transmet par :</h3>
<ul>
    <li>Rapports sexuels non protégés</li>
    <li>Partage d'aiguilles</li>
    <li>Transmission mère-enfant pendant la grossesse/accouchement/allaitement</li>
</ul>

<h3>Le VIH NE SE transmet PAS par :</h3>
<ul>
    <li>Les câlins, les poignées de main</li>
    <li>Le partage de nourriture ou d'ustensiles</li>
    <li>Les piqûres de moustiques</li>
</ul>

<hr/>

<h2>Dépistage et traitement</h2>
<p>Le dépistage du VIH est gratuit, confidentiel et rapide. Le TAR est disponible gratuitement dans la plupart des pays africains.</p>
<p><b>Indétectable = Intransmissible (I=I)</b></p>

<hr/>

<h2>Prévention</h2>
<ul>
    <li>Utiliser des préservatifs correctement à chaque fois</li>
    <li>Se faire dépister régulièrement</li>
    <li>PrEP : Pilule quotidienne pour les personnes à haut risque</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">ONUSIDA</div>
        <a href="https://www.unaids.org/fr">www.unaids.org/fr</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur le VIH/SIDA : 39 millions de personnes vivent avec le VIH. Dépistage, traitement TAR et prévention.",
                source = "OMS, ONUSIDA",
                category = "Santé Sexuelle"
            ),
            
            // SANTÉ MATERNELLE - French
            Article(
                title = "Santé maternelle",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>800 femmes meurent chaque jour de causes évitables liées à la grossesse.</li>
        <li>94% des décès maternels surviennent dans les pays à faible revenu.</li>
        <li>L'OMS recommande 8 consultations prénatales pendant la grossesse.</li>
    </ul>
</div>

<h2>Soins prénatals</h2>
<ul>
    <li>Mesure de la tension artérielle et du poids</li>
    <li>Vérification de la croissance et du rythme cardiaque du bébé</li>
    <li>Conseils nutritionnels</li>
</ul>

<hr/>

<h2 class="warning">⚠️ Signes de danger - Consulter immédiatement :</h2>
<ul>
    <li>Saignement vaginal</li>
    <li>Maux de tête sévères ou vision floue</li>
    <li>Forte fièvre</li>
    <li>Convulsions</li>
</ul>

<hr/>

<h2>Allaitement</h2>
<ul>
    <li>Commencer dans l'heure suivant la naissance</li>
    <li>Allaitement exclusif pendant 6 mois</li>
    <li>Continuer jusqu'à 2 ans</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/health-topics/maternal-health">www.who.int/fr/health-topics/maternal-health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur la santé maternelle : 800 femmes meurent chaque jour. Soins prénatals et signes de danger.",
                source = "OMS, UNICEF",
                category = "Santé Maternelle"
            ),
            
            // ALIMENTATION SAINE - French
            Article(
                title = "Alimentation saine",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Une alimentation saine protège contre la malnutrition et les maladies.</li>
        <li>Une mauvaise alimentation est un risque majeur pour la santé mondiale.</li>
        <li>L'apport énergétique doit être équilibré avec la dépense énergétique.</li>
    </ul>
</div>

<h2>Groupes alimentaires</h2>
<h3>1. Aliments énergétiques :</h3>
<p>Maïs, riz, manioc, pommes de terre, pain</p>

<h3>2. Aliments constructeurs :</h3>
<p>Haricots, poisson, œufs, viande, lait</p>

<h3>3. Aliments protecteurs :</h3>
<p>Légumes, fruits</p>

<hr/>

<h2>Conseils pour une alimentation saine</h2>
<ul>
    <li>Manger 3 repas par jour</li>
    <li>Manger des fruits et légumes tous les jours</li>
    <li>Limiter le sucre, le sel et les aliments transformés</li>
    <li>Boire 6-8 verres d'eau par jour</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/healthy-diet">www.who.int/fr/news-room/fact-sheets/detail/healthy-diet</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur l'alimentation saine : essentielle pour prévenir la malnutrition et les maladies.",
                source = "OMS, FAO",
                category = "Nutrition"
            ),
            
            // EAU POTABLE - French
            Article(
                title = "Eau potable",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>L'eau contaminée transmet des maladies comme le choléra et la typhoïde.</li>
        <li>2 milliards de personnes utilisent des sources d'eau contaminées.</li>
        <li>L'eau potable pourrait prévenir 400 000 décès par an.</li>
    </ul>
</div>

<h2>Traitement de l'eau</h2>
<h3>1. Ébullition :</h3>
<ul>
    <li>Faire bouillir pendant au moins 1 minute</li>
    <li>Laisser refroidir naturellement</li>
    <li>Conserver dans un récipient propre</li>
</ul>

<h3>2. Chloration :</h3>
<p>Utiliser des comprimés de purification ou 2 gouttes d'eau de Javel par litre</p>

<hr/>

<h2>Hygiène des mains</h2>
<p>Se laver les mains au savon :</p>
<ul>
    <li>Avant de manger</li>
    <li>Après être allé aux toilettes</li>
    <li>Après avoir changé les couches</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/drinking-water">www.who.int/fr/news-room/fact-sheets/detail/drinking-water</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur l'eau potable : 2 milliards de personnes manquent d'eau potable. Traitement de l'eau et hygiène des mains.",
                source = "OMS, UNICEF",
                category = "Hygiène et Assainissement"
            ),
            
            // PREMIERS SECOURS - French
            Article(
                title = "Premiers secours",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Les premiers secours sont les soins immédiats jusqu'à l'arrivée de l'aide professionnelle.</li>
        <li>Les compétences de base peuvent sauver des vies.</li>
        <li>Objectifs : préserver la vie, éviter l'aggravation, favoriser la guérison.</li>
    </ul>
</div>

<h2>Saignement</h2>
<ul>
    <li>Appliquer une pression ferme avec un tissu propre</li>
    <li>Élever le membre blessé au-dessus du cœur</li>
    <li>Obtenir de l'aide d'urgence pour les saignements graves</li>
</ul>

<hr/>

<h2>Brûlures</h2>
<ul>
    <li>Refroidir à l'eau fraîche pendant 10-20 minutes</li>
    <li>NE PAS appliquer de beurre ou d'huile</li>
    <li>Couvrir d'un bandage propre</li>
</ul>

<hr/>

<h2>Étouffement</h2>
<p>Si la personne ne peut pas respirer : Se placer derrière elle et donner des poussées abdominales rapides vers le haut au-dessus du nombril.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Croix-Rouge / Croissant-Rouge</div>
        <a href="https://www.ifrc.org/fr/first-aid">www.ifrc.org/fr/first-aid</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Guide essentiel des premiers secours : saignement, brûlures, étouffement. Compétences vitales pour tous.",
                source = "Croix-Rouge, OMS",
                category = "Soins d'Urgence"
            ),
            
            // SANTÉ MENTALE - French
            Article(
                title = "Santé mentale",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>1 personne sur 8 vit avec un trouble mental.</li>
        <li>Les troubles de santé mentale sont traitables.</li>
        <li>La santé mentale est aussi importante que la santé physique.</li>
    </ul>
</div>

<h2>Signes de dépression :</h2>
<ul>
    <li>Tristesse persistante</li>
    <li>Perte d'intérêt</li>
    <li>Changements de sommeil</li>
    <li>Fatigue</li>
</ul>

<hr/>

<h2>Quand demander de l'aide</h2>
<ul>
    <li>Symptômes durant plus de 2 semaines</li>
    <li>Difficulté avec les activités quotidiennes</li>
    <li>Pensées d'automutilation</li>
</ul>

<p class="warning"><b>⚠️ CRISE : Pensées suicidaires = urgence médicale. Demander de l'aide immédiatement.</b></p>

<hr/>

<h2>Mode de vie sain</h2>
<ul>
    <li>Faire de l'exercice 30 minutes par jour</li>
    <li>Dormir 7-8 heures</li>
    <li>Maintenir des liens sociaux</li>
    <li>Passer du temps dans la nature</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/mental-health">www.who.int/fr/news-room/fact-sheets/detail/mental-health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fiche OMS sur la santé mentale : 1 personne sur 8 a un trouble mental. Symptômes et soutien.",
                source = "OMS",
                category = "Santé Mentale"
            )
        )
    }
}
