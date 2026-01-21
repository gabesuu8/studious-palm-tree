package com.example.helloapp.service

import com.example.helloapp.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleFetcher {
    
    suspend fun fetchHealthcareArticles(): List<Article> = withContext(Dispatchers.IO) {
        getSampleArticles()
    }
    
    private fun getSampleArticles(): List<Article> {
        return listOf(
            // MALARIA
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
            
            // DIARRHEA
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
        <li>Most deaths from diarrhoea are preventable using simple, low-cost interventions.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Diarrhoea is the passage of 3 or more loose or liquid stools per day. It is usually a symptom of gastrointestinal infection caused by bacteria, viruses or parasites.</p>
<p>Most people who die from diarrhoea actually die from severe dehydration and fluid loss.</p>

<hr/>

<h2>Signs of dehydration</h2>

<h3>Mild to moderate dehydration:</h3>
<ul>
    <li>Thirst</li>
    <li>Dry mouth and lips</li>
    <li>Decreased urination</li>
    <li>Slightly sunken eyes</li>
</ul>

<h3 class="warning">Severe dehydration (EMERGENCY):</h3>
<ul>
    <li>Very sunken eyes</li>
    <li>Unable to drink or drinks poorly</li>
    <li>Skin pinch goes back very slowly (>2 seconds)</li>
    <li>Lethargy or unconsciousness</li>
    <li>No tears when crying</li>
    <li>No urination for 6+ hours</li>
</ul>

<p class="warning"><b>⚠️ Severe dehydration can cause death within hours if not treated!</b></p>

<hr/>

<h2>Treatment</h2>

<h3>Oral Rehydration Therapy (ORS)</h3>
<p>ORS is the most important treatment for diarrhoea. It replaces fluid and essential salts lost.</p>

<div class="highlight-box">
    <b>🏠 How to make ORS at home:</b><br/>
    Mix in 1 litre of clean (boiled and cooled) water:<br/>
    • 6 level teaspoons of sugar<br/>
    • ½ level teaspoon of salt
</div>

<h3>How much ORS to give:</h3>
<ul>
    <li><b>Children under 2 years:</b> 50-100 ml after each loose stool</li>
    <li><b>Children 2-10 years:</b> 100-200 ml after each loose stool</li>
    <li><b>Older children and adults:</b> As much as wanted</li>
</ul>

<h3>Zinc supplements (for children):</h3>
<ul>
    <li>Under 6 months: 10 mg daily for 10-14 days</li>
    <li>Over 6 months: 20 mg daily for 10-14 days</li>
</ul>

<hr/>

<h2>When to seek medical help</h2>
<p>Go to a health facility immediately if:</p>
<ul>
    <li>Blood in stool</li>
    <li>Fever higher than 38.5°C (101.3°F)</li>
    <li>Signs of severe dehydration</li>
    <li>Diarrhoea lasting more than 3 days</li>
    <li>Child refuses to eat or drink</li>
    <li>Repeated vomiting</li>
</ul>

<hr/>

<h2>Prevention</h2>

<h3>Safe water:</h3>
<ul>
    <li>Boil water for at least 1 minute before drinking</li>
    <li>Store water in clean, covered containers</li>
    <li>Use water purification tablets when available</li>
</ul>

<h3>Hand hygiene - Wash hands with soap:</h3>
<ul>
    <li>Before eating and preparing food</li>
    <li>After using the toilet</li>
    <li>After changing diapers</li>
</ul>

<h3>Food safety:</h3>
<ul>
    <li>Cook food thoroughly</li>
    <li>Eat food while hot</li>
    <li>Wash fruits and vegetables with safe water</li>
    <li>Keep food covered</li>
</ul>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Continue breastfeeding infants during and after diarrhoea</li>
    <li>Give small, frequent meals during recovery</li>
    <li>Offer bananas, rice, toast, and potatoes (easy to digest)</li>
    <li>Avoid fatty, spicy, or sugary foods during illness</li>
    <li>Drink plenty of clean water throughout the day</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/diarrhoeal-disease">www.who.int/news-room/fact-sheets/detail/diarrhoeal-disease</a>
    </div>
    <div class="source-item">
        <div class="source-name">UNICEF</div>
        <a href="https://www.unicef.org/health/diarrhoea">www.unicef.org/health/diarrhoea</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on diarrhoeal disease: second leading cause of death in children under 5. Learn about ORS treatment and prevention.",
                source = "WHO, UNICEF",
                category = "Child Health"
            ),
            
            // CHOLERA
            Article(
                title = "Cholera",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Cholera is an acute diarrhoeal infection caused by ingestion of contaminated food or water.</li>
        <li>Cholera affects 1-4 million people annually, causing 21,000-143,000 deaths worldwide.</li>
        <li>Cholera can kill within hours if left untreated.</li>
        <li>Up to 80% of cases can be successfully treated with oral rehydration salts.</li>
        <li>Safe water and sanitation are the most effective ways to prevent cholera.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Cholera is an extremely virulent disease that can cause severe acute watery diarrhoea. It takes between 12 hours and 5 days for a person to show symptoms after ingesting contaminated food or water.</p>

<hr/>

<h2>Symptoms</h2>

<h3>Mild to moderate:</h3>
<ul>
    <li>Watery diarrhoea</li>
    <li>Mild dehydration</li>
    <li>Thirst</li>
</ul>

<h3 class="warning">Severe cholera (EMERGENCY):</h3>
<ul>
    <li>Profuse watery diarrhoea ("rice water" stools)</li>
    <li>Vomiting</li>
    <li>Leg cramps</li>
    <li>Rapid heart rate</li>
    <li>Sunken eyes, dry mouth</li>
    <li>Wrinkled skin on fingers</li>
</ul>

<p class="warning"><b>⚠️ Severe cholera can cause death within hours if not treated!</b></p>

<hr/>

<h2>Treatment</h2>
<p>Cholera is easily treatable with oral rehydration solution (ORS).</p>

<h3>Treatment steps:</h3>
<ul>
    <li><b>Rehydration:</b> Start ORS immediately. Give as much as the person can drink.</li>
    <li><b>Severe cases:</b> May require intravenous fluids at a health facility.</li>
    <li><b>Antibiotics:</b> Reduce duration; prescribed by health workers.</li>
</ul>

<p>With prompt treatment, the fatality rate should remain below 1%.</p>

<hr/>

<h2>Prevention</h2>

<h3>Safe water – "Boil it, filter it, or forget it":</h3>
<ul>
    <li>Boil water for at least 1 minute</li>
    <li>Use water purification tablets or filters</li>
    <li>Drink only from known safe sources</li>
</ul>

<h3>Food safety:</h3>
<ul>
    <li>Cook food thoroughly, especially seafood</li>
    <li>Eat food while hot</li>
    <li>Avoid raw foods during outbreaks</li>
    <li>Peel fruits yourself</li>
</ul>

<h3>Hygiene:</h3>
<ul>
    <li>Wash hands frequently with soap</li>
    <li>Use latrines or toilets</li>
    <li>Dispose of faeces safely</li>
</ul>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Eat freshly cooked, hot meals</li>
    <li>Maintain good nutrition for strong immunity</li>
    <li>Breastfeed infants exclusively for 6 months</li>
    <li>Keep water containers clean and covered</li>
    <li>Participate in community clean-up campaigns</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/cholera">www.who.int/news-room/fact-sheets/detail/cholera</a>
    </div>
    <div class="source-item">
        <div class="source-name">Global Task Force on Cholera Control</div>
        <a href="https://www.gtfcc.org">www.gtfcc.org</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on cholera: acute diarrhoeal infection that can kill within hours. Learn about ORS treatment and safe water prevention.",
                source = "WHO, GTFCC",
                category = "Infectious Diseases"
            ),
            
            // TYPHOID
            Article(
                title = "Typhoid",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Typhoid fever is caused by the bacterium Salmonella typhi.</li>
        <li>An estimated 11-20 million people get sick from typhoid each year.</li>
        <li>Between 128,000 and 161,000 people die from typhoid annually.</li>
        <li>Typhoid spreads through contaminated food and water.</li>
        <li>Two vaccines are available to prevent typhoid.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Typhoid fever is a life-threatening infection caused by the bacterium Salmonella typhi. It is usually spread through contaminated food or water.</p>
<p>Typhoid fever is more common in places with poor sanitation and lack of safe drinking water.</p>

<hr/>

<h2>Symptoms</h2>
<p>Symptoms usually develop 1-3 weeks after exposure:</p>

<h3>Week 1:</h3>
<ul>
    <li>Gradually increasing fever</li>
    <li>Headache</li>
    <li>Weakness and fatigue</li>
    <li>Muscle aches</li>
    <li>Loss of appetite</li>
</ul>

<h3>Week 2-3:</h3>
<ul>
    <li>High fever (39-40°C / 102-104°F)</li>
    <li>Abdominal pain and bloating</li>
    <li>Constipation or diarrhoea</li>
    <li>Rose-coloured spots on chest and abdomen</li>
</ul>

<h3 class="warning">Danger signs (seek immediate care):</h3>
<ul>
    <li>Severe abdominal pain</li>
    <li>Bloody stools</li>
    <li>Confusion</li>
    <li>Persistent high fever despite treatment</li>
</ul>

<hr/>

<h2>Treatment</h2>
<p>Typhoid fever is treated with antibiotics. Without treatment, the fatality rate can reach 10-30%.</p>

<h3>Medical treatment:</h3>
<ul>
    <li>Blood test confirms diagnosis</li>
    <li>Antibiotics are essential</li>
    <li>Complete the full course (usually 7-14 days)</li>
</ul>

<h3>Home care:</h3>
<ul>
    <li>Rest in bed</li>
    <li>Drink plenty of clean fluids</li>
    <li>Eat small, frequent meals</li>
    <li>Take paracetamol for fever</li>
</ul>

<hr/>

<h2>Prevention</h2>

<h3>Safe water:</h3>
<ul>
    <li>Boil or treat all drinking water</li>
    <li>Avoid ice from unknown sources</li>
    <li>Use safe water for brushing teeth</li>
</ul>

<h3>Food safety:</h3>
<ul>
    <li>Eat thoroughly cooked, hot foods</li>
    <li>Avoid raw vegetables and salads from unknown sources</li>
    <li>Peel fruits yourself</li>
</ul>

<h3>Vaccination:</h3>
<ul>
    <li>Injectable vaccine: single dose, protects for 3+ years</li>
    <li>Oral vaccine: 4 doses, protects for 5 years</li>
</ul>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Eat a balanced diet to maintain immunity</li>
    <li>Drink at least 2 litres of safe water daily</li>
    <li>Get adequate sleep (7-8 hours)</li>
    <li>Exercise regularly when healthy</li>
    <li>Complete vaccination schedules</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/typhoid">www.who.int/news-room/fact-sheets/detail/typhoid</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/typhoid-fever">www.cdc.gov/typhoid-fever</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on typhoid: 11-20 million cases annually. Learn about symptoms, antibiotic treatment, and prevention through safe water.",
                source = "WHO, CDC",
                category = "Infectious Diseases"
            ),
            
            // HIV/AIDS
            Article(
                title = "HIV/AIDS",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>HIV (human immunodeficiency virus) attacks the body's immune system.</li>
        <li>Approximately 39 million people are living with HIV globally.</li>
        <li>In 2022, 630,000 people died from HIV-related causes.</li>
        <li>HIV can be suppressed by treatment with antiretroviral therapy (ART).</li>
        <li>With proper treatment, people with HIV can live long, healthy lives.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>HIV attacks the body's immune system. If HIV is not treated, it can lead to AIDS.</p>
<p>There is no effective cure for HIV. However, with proper medical care, HIV can be controlled. Treatment with ART can reduce the amount of HIV in the blood to undetectable levels.</p>

<hr/>

<h2>Transmission</h2>

<h3>HIV IS spread through:</h3>
<ul>
    <li>Unprotected sexual contact</li>
    <li>Sharing needles or syringes</li>
    <li>Mother-to-child during pregnancy, birth, or breastfeeding</li>
    <li>Blood transfusion with infected blood (rare)</li>
</ul>

<h3>HIV is NOT spread through:</h3>
<ul>
    <li>Hugging, shaking hands, casual contact</li>
    <li>Sharing food, drinks, or utensils</li>
    <li>Mosquito bites</li>
    <li>Air, water, or saliva</li>
</ul>

<hr/>

<h2>Symptoms</h2>

<h3>Early infection (2-4 weeks):</h3>
<ul>
    <li>Flu-like symptoms</li>
    <li>Fever and fatigue</li>
    <li>Swollen lymph nodes</li>
    <li>Sore throat and rash</li>
</ul>

<h3>Chronic HIV:</h3>
<p>Many people have no symptoms for years.</p>

<h3>AIDS (without treatment):</h3>
<ul>
    <li>Rapid weight loss</li>
    <li>Recurring fever</li>
    <li>Extreme fatigue</li>
    <li>Frequent infections</li>
</ul>

<hr/>

<h2>Testing</h2>
<p>HIV testing is the only way to know your status. Testing is:</p>
<ul>
    <li><b>Free</b> at most health facilities</li>
    <li><b>Confidential</b></li>
    <li><b>Quick</b> – results in 15-30 minutes</li>
</ul>

<hr/>

<h2>Treatment</h2>

<h3>Antiretroviral Therapy (ART):</h3>
<ul>
    <li>Available free in most African countries</li>
    <li>Take medications daily as prescribed</li>
    <li>Reduces HIV to undetectable levels</li>
    <li><b>Undetectable = Untransmittable (U=U)</b></li>
</ul>

<p><b>Important:</b> Never skip doses. Attend all clinic appointments.</p>

<hr/>

<h2>Prevention</h2>

<h3>Behavioural prevention:</h3>
<ul>
    <li>Use condoms correctly every time</li>
    <li>Limit number of sexual partners</li>
    <li>Get tested regularly</li>
    <li>Never share needles</li>
</ul>

<h3>Medical prevention:</h3>
<ul>
    <li><b>PrEP:</b> Daily pill that prevents HIV for high-risk individuals</li>
    <li><b>PEP:</b> Emergency medication within 72 hours of exposure</li>
</ul>

<hr/>

<h2>Healthy lifestyle with HIV</h2>
<ul>
    <li>Take ART exactly as prescribed</li>
    <li>Eat a balanced, nutritious diet</li>
    <li>Exercise regularly (30 minutes daily)</li>
    <li>Get adequate sleep (7-8 hours)</li>
    <li>Avoid smoking and limit alcohol</li>
    <li>Attend all medical appointments</li>
    <li>Join support groups for emotional wellbeing</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">UNAIDS</div>
        <a href="https://www.unaids.org">www.unaids.org</a>
    </div>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/hiv-aids">www.who.int/news-room/fact-sheets/detail/hiv-aids</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on HIV/AIDS: 39 million people living with HIV globally. Learn about testing, ART treatment, and prevention.",
                source = "WHO, UNAIDS",
                category = "Sexual Health"
            ),
            
            // MATERNAL HEALTH
            Article(
                title = "Maternal health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Every day, approximately 800 women die from preventable causes related to pregnancy and childbirth.</li>
        <li>94% of maternal deaths occur in low and middle-income countries.</li>
        <li>Most maternal deaths are preventable with proper care.</li>
        <li>Skilled care before, during and after childbirth can save lives.</li>
        <li>WHO recommends at least 8 antenatal care contacts during pregnancy.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Maternal health refers to the health of women during pregnancy, childbirth, and the postnatal period.</p>
<p>The major complications that account for 75% of maternal deaths are: severe bleeding, infections, high blood pressure, complications from delivery, and unsafe abortion.</p>

<hr/>

<h2>Antenatal care</h2>
<p>WHO recommends a minimum of 8 contacts with a health provider during pregnancy.</p>

<h3>What happens at antenatal visits:</h3>
<ul>
    <li>Blood pressure measurement</li>
    <li>Weight monitoring</li>
    <li>Urine and blood tests</li>
    <li>Checking baby's growth and heartbeat</li>
    <li>Nutrition counselling</li>
    <li>Birth planning</li>
</ul>

<hr/>

<h2 class="warning">⚠️ Danger signs</h2>

<h3>During pregnancy – seek immediate care if:</h3>
<ul>
    <li>Vaginal bleeding</li>
    <li>Severe headache or blurred vision</li>
    <li>High fever</li>
    <li>Severe abdominal pain</li>
    <li>Reduced or no baby movement</li>
    <li>Swelling of face and hands</li>
    <li>Convulsions</li>
</ul>

<h3>During labour:</h3>
<ul>
    <li>Labour lasting more than 12 hours</li>
    <li>Heavy bleeding</li>
    <li>Cord coming out first</li>
</ul>

<h3>After delivery:</h3>
<ul>
    <li>Heavy bleeding (soaking >1 pad/hour)</li>
    <li>High fever</li>
    <li>Foul-smelling discharge</li>
</ul>

<hr/>

<h2>Essential care during pregnancy</h2>

<h3>Nutrition:</h3>
<ul>
    <li>Eat diverse, nutritious foods</li>
    <li>One extra meal daily</li>
    <li>Iron-rich foods (green vegetables, meat, beans)</li>
    <li>Take iron and folic acid supplements</li>
</ul>

<h3>What to avoid:</h3>
<ul>
    <li>Alcohol – no safe amount during pregnancy</li>
    <li>Smoking</li>
    <li>Heavy lifting</li>
    <li>Self-medication</li>
</ul>

<hr/>

<h2>Breastfeeding</h2>
<ul>
    <li>Start within 1 hour of birth</li>
    <li>Give only breast milk for the first 6 months</li>
    <li>Breastfeed on demand (8-12 times daily)</li>
    <li>Continue breastfeeding up to 2 years</li>
</ul>

<h3>Benefits:</h3>
<ul>
    <li>Best nutrition for baby</li>
    <li>Protects against infections</li>
    <li>Helps mother recover</li>
    <li>Free and always available</li>
</ul>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Eat 3 meals plus snacks daily</li>
    <li>Drink plenty of clean water (8-10 glasses)</li>
    <li>Get light exercise (walking 30 minutes daily)</li>
    <li>Sleep 8 hours at night</li>
    <li>Avoid stress</li>
    <li>Attend all antenatal and postnatal visits</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/health-topics/maternal-health">www.who.int/health-topics/maternal-health</a>
    </div>
    <div class="source-item">
        <div class="source-name">UNICEF</div>
        <a href="https://www.unicef.org/health/maternal-and-newborn-health">www.unicef.org/health/maternal-and-newborn-health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on maternal health: 800 women die daily from preventable causes. Learn about antenatal care, danger signs, and breastfeeding.",
                source = "WHO, UNICEF",
                category = "Maternal Health"
            ),
            
            // NUTRITION
            Article(
                title = "Healthy diet",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>A healthy diet helps protect against malnutrition and noncommunicable diseases.</li>
        <li>Unhealthy diet and lack of physical activity are leading global health risks.</li>
        <li>Healthy dietary practices start early in life with breastfeeding.</li>
        <li>Energy intake should be in balance with energy expenditure.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>A healthy diet is essential for good health and nutrition. It protects you against chronic diseases such as heart disease, diabetes and cancer.</p>

<hr/>

<h2>Food groups</h2>

<h3>1. Energy foods (carbohydrates):</h3>
<p>Maize, millet, rice, cassava, yams, potatoes, bread</p>
<p><i>Provide energy for daily activities</i></p>

<h3>2. Body-building foods (proteins):</h3>
<p>Beans, lentils, fish, chicken, eggs, meat, milk</p>
<p><i>Build and repair muscles and tissues</i></p>

<h3>3. Protective foods (vitamins & minerals):</h3>
<p>Dark green vegetables, orange vegetables, fruits</p>
<p><i>Protect against disease</i></p>

<h3>4. Healthy fats:</h3>
<p>Groundnut oil, sunflower oil, avocado, nuts, seeds</p>
<p><i>Essential for brain function</i></p>

<hr/>

<h2>Eating for strong immunity</h2>

<h3>Vitamin A (fights infections):</h3>
<p>Orange/yellow fruits and vegetables, dark green leaves, eggs, liver</p>

<h3>Vitamin C (boosts immune system):</h3>
<p>Citrus fruits, tomatoes, peppers, guava, mango</p>

<h3>Iron (prevents anaemia):</h3>
<p>Red meat, liver, beans, dark green vegetables</p>
<p><i>Eat with vitamin C for better absorption</i></p>

<h3>Zinc (supports immune function):</h3>
<p>Meat, fish, beans, nuts, whole grains</p>

<hr/>

<h2>Infant and child nutrition</h2>

<h3>0-6 months:</h3>
<ul>
    <li>Exclusive breastfeeding only</li>
    <li>No water, juices, or other foods</li>
</ul>

<h3>6-12 months:</h3>
<ul>
    <li>Continue breastfeeding</li>
    <li>Start soft, mashed foods</li>
    <li>Feed 2-3 times daily plus snacks</li>
</ul>

<h3>1-2 years:</h3>
<ul>
    <li>Continue breastfeeding</li>
    <li>Family foods, cut small</li>
    <li>Feed 3-4 times daily plus snacks</li>
</ul>

<hr/>

<h2>Food safety</h2>
<ul>
    <li><b>Clean:</b> Wash hands and surfaces often</li>
    <li><b>Separate:</b> Keep raw meat away from other foods</li>
    <li><b>Cook:</b> Cook food thoroughly</li>
    <li><b>Chill:</b> Refrigerate promptly</li>
</ul>

<hr/>

<h2>Healthy eating recommendations</h2>
<ul>
    <li>Eat 3 meals daily – don't skip breakfast</li>
    <li>Include a variety of foods from all food groups</li>
    <li>Eat fruits and vegetables every day</li>
    <li>Choose whole grains over refined grains</li>
    <li>Limit sugar, salt, and processed foods</li>
    <li>Drink 6-8 glasses of clean water daily</li>
</ul>

<h3>Affordable nutrition tips:</h3>
<ul>
    <li>Beans and lentils are cheap protein sources</li>
    <li>Buy seasonal fruits and vegetables</li>
    <li>Grow a small vegetable garden if possible</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/healthy-diet">www.who.int/news-room/fact-sheets/detail/healthy-diet</a>
    </div>
    <div class="source-item">
        <div class="source-name">FAO</div>
        <a href="https://www.fao.org/nutrition">www.fao.org/nutrition</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on healthy diet: essential for preventing malnutrition and disease. Learn about food groups, infant nutrition, and eating tips.",
                source = "WHO, FAO",
                category = "Nutrition"
            ),
            
            // WATER AND SANITATION
            Article(
                title = "Drinking-water",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Contaminated water can transmit diseases such as diarrhoea, cholera, dysentery, typhoid, and polio.</li>
        <li>Some 2 billion people use a drinking water source contaminated with faeces.</li>
        <li>Contaminated drinking water causes an estimated 485,000 diarrhoeal deaths each year.</li>
        <li>Safe water, sanitation, and hygiene could prevent around 400,000 deaths annually.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Safe and readily available water is important for public health. Improved water supply and sanitation can boost economic growth and reduce poverty.</p>

<hr/>

<h2>Water treatment methods</h2>

<h3>1. Boiling (most effective):</h3>
<ul>
    <li>Bring water to a rolling boil</li>
    <li>Boil for at least 1 minute</li>
    <li>Let cool naturally</li>
    <li>Store in clean, covered container</li>
</ul>

<h3>2. Chlorine treatment:</h3>
<ul>
    <li>Use water purification tablets as directed</li>
    <li>Or add 2 drops of bleach per litre</li>
    <li>Wait 30 minutes before drinking</li>
</ul>

<h3>3. Filtration:</h3>
<ul>
    <li>Use ceramic, cloth, or commercial filters</li>
    <li>Clean filters regularly</li>
</ul>

<h3>4. Solar disinfection (SODIS):</h3>
<ul>
    <li>Fill clear plastic bottles with water</li>
    <li>Place in direct sunlight for 6+ hours</li>
</ul>

<hr/>

<h2>Safe water storage</h2>
<ul>
    <li>Use clean containers with covers</li>
    <li>Clean containers regularly with soap</li>
    <li>Don't put hands into stored water</li>
    <li>Use a clean cup or ladle</li>
    <li>Keep containers off the ground</li>
</ul>

<hr/>

<h2>Hand hygiene</h2>

<h3>When to wash hands:</h3>
<ul>
    <li>Before eating or preparing food</li>
    <li>After using the toilet</li>
    <li>After changing diapers</li>
    <li>After touching animals</li>
    <li>After coughing or sneezing</li>
</ul>

<h3>How to wash hands properly:</h3>
<ul>
    <li>Wet hands with clean water</li>
    <li>Apply soap</li>
    <li>Rub hands together for 20 seconds</li>
    <li>Clean between fingers and under nails</li>
    <li>Rinse and dry with clean cloth</li>
</ul>

<p><b>No soap?</b> Use ash, sand, or lemon juice.</p>

<hr/>

<h2>Sanitation</h2>

<h3>Safe toilet use:</h3>
<ul>
    <li>Use latrines or toilets – avoid open defecation</li>
    <li>Build latrines at least 30 metres from water sources</li>
    <li>Always wash hands after using the toilet</li>
</ul>

<hr/>

<h2>Healthy lifestyle recommendations</h2>
<ul>
    <li>Always treat water before drinking if safety is uncertain</li>
    <li>Wash hands multiple times daily</li>
    <li>Keep fingernails short and clean</li>
    <li>Bathe regularly</li>
    <li>Keep cooking and living areas clean</li>
    <li>Protect community water sources</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/drinking-water">www.who.int/news-room/fact-sheets/detail/drinking-water</a>
    </div>
    <div class="source-item">
        <div class="source-name">UNICEF WASH</div>
        <a href="https://www.unicef.org/wash">www.unicef.org/wash</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on drinking water: 2 billion people lack safe water. Learn about water treatment, storage, hand hygiene, and sanitation.",
                source = "WHO, UNICEF",
                category = "Hygiene & Sanitation"
            ),
            
            // FIRST AID
            Article(
                title = "First aid",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>First aid is the immediate care given to a sick or injured person until professional help arrives.</li>
        <li>Basic first aid skills can save lives in emergencies.</li>
        <li>The goals: preserve life, prevent worsening, promote recovery.</li>
        <li>Anyone can learn first aid.</li>
    </ul>
</div>

<h2>General principles</h2>
<ul>
    <li><b>Stay calm</b> – think clearly before acting</li>
    <li><b>Ensure safety</b> – check for dangers</li>
    <li><b>Call for help</b> – get emergency assistance</li>
    <li><b>Provide care</b> – give appropriate first aid</li>
</ul>

<hr/>

<h2>Bleeding</h2>

<h3>For minor cuts:</h3>
<ul>
    <li>Wash your hands first</li>
    <li>Clean wound with clean water</li>
    <li>Apply pressure with clean cloth</li>
    <li>Cover with clean bandage</li>
</ul>

<h3 class="warning">For severe bleeding:</h3>
<ul>
    <li>Apply firm, direct pressure with clean cloth</li>
    <li>Keep pressing – don't remove the cloth</li>
    <li>If blood soaks through, add more cloth on top</li>
    <li>Raise injured limb above heart level</li>
    <li>Get emergency help immediately</li>
</ul>

<hr/>

<h2>Burns</h2>

<h3>For minor burns:</h3>
<ul>
    <li>Cool with clean, cool water for 10-20 minutes</li>
    <li>Remove jewelry near the burn</li>
    <li>Cover with clean bandage</li>
</ul>

<h3 class="warning">For severe burns:</h3>
<ul>
    <li>Cool with water (not ice)</li>
    <li>Do NOT break blisters</li>
    <li>Do NOT apply creams, butter, or oil</li>
    <li>Cover loosely with clean cloth</li>
    <li>Seek immediate medical care</li>
</ul>

<hr/>

<h2>Choking</h2>

<h3>If person can cough:</h3>
<ul>
    <li>Encourage them to keep coughing</li>
</ul>

<h3 class="warning">If person cannot breathe or cough:</h3>
<ul>
    <li>Stand behind them</li>
    <li>Place fist above belly button</li>
    <li>Give quick upward thrusts</li>
    <li>Repeat until object comes out</li>
</ul>

<h3>For infants:</h3>
<ul>
    <li>Lay baby face-down on forearm</li>
    <li>Give 5 back blows between shoulder blades</li>
    <li>Turn over, give 5 chest thrusts</li>
    <li>Repeat until object comes out</li>
</ul>

<hr/>

<h2>Unconsciousness</h2>
<p>If person is unconscious but breathing:</p>
<ul>
    <li>Check for response – tap and call their name</li>
    <li>Call for help</li>
    <li>Place in recovery position (on their side)</li>
    <li>Monitor breathing until help arrives</li>
</ul>

<hr/>

<h2>Snake bites</h2>

<h3>DO:</h3>
<ul>
    <li>Keep person calm and still</li>
    <li>Remove jewelry near bite</li>
    <li>Keep bitten area below heart level</li>
    <li>Get to hospital immediately</li>
</ul>

<h3 class="warning">DON'T:</h3>
<ul>
    <li>Cut the wound</li>
    <li>Try to suck out venom</li>
    <li>Apply ice or tourniquet</li>
</ul>

<hr/>

<h2>First aid kit essentials</h2>
<ul>
    <li>Clean bandages and gauze</li>
    <li>Adhesive tape and plasters</li>
    <li>Scissors and tweezers</li>
    <li>Antiseptic solution</li>
    <li>Paracetamol</li>
    <li>ORS packets</li>
    <li>Thermometer</li>
    <li>Emergency contact numbers</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Red Cross / Red Crescent</div>
        <a href="https://www.ifrc.org/first-aid">www.ifrc.org/first-aid</a>
    </div>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/emergencies">www.who.int/emergencies</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Essential first aid guide: bleeding, burns, choking, unconsciousness, and snake bites. Life-saving skills everyone should know.",
                source = "Red Cross, WHO",
                category = "Emergency Care"
            ),
            
            // MENTAL HEALTH
            Article(
                title = "Mental health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>1 in every 8 people in the world lives with a mental disorder.</li>
        <li>People with severe mental health conditions die 10-20 years earlier than the general population.</li>
        <li>Mental health conditions are treatable, and recovery is possible.</li>
        <li>Mental health is just as important as physical health.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Mental health is a state of mental well-being that enables people to cope with the stresses of life, realize their abilities, learn well and work well, and contribute to their community.</p>
<p>Mental health problems are NOT a sign of weakness. They are medical conditions like any other.</p>

<hr/>

<h2>Common conditions</h2>

<h3>Depression - Signs may include:</h3>
<ul>
    <li>Persistent sadness or low mood</li>
    <li>Loss of interest in activities</li>
    <li>Changes in sleep (too much or too little)</li>
    <li>Fatigue and lack of energy</li>
    <li>Difficulty concentrating</li>
    <li>Feelings of worthlessness</li>
    <li>Thoughts of death or suicide</li>
</ul>

<h3>Anxiety - Signs may include:</h3>
<ul>
    <li>Excessive worry</li>
    <li>Restlessness</li>
    <li>Racing heart</li>
    <li>Difficulty sleeping</li>
    <li>Avoiding situations</li>
    <li>Physical symptoms (headaches, stomach problems)</li>
</ul>

<hr/>

<h2>When to seek help</h2>
<p>Seek professional help if you experience:</p>
<ul>
    <li>Persistent sadness or worry lasting more than 2 weeks</li>
    <li>Difficulty performing daily activities</li>
    <li>Thoughts of harming yourself or others</li>
    <li>Using substances to cope</li>
    <li>Withdrawal from friends and family</li>
</ul>

<p class="warning"><b>⚠️ CRISIS: If you or someone has thoughts of suicide, seek immediate help. This is a medical emergency.</b></p>

<hr/>

<h2>Treatment</h2>
<p>Mental health conditions are treatable:</p>
<ul>
    <li><b>Talking therapies:</b> Counselling, cognitive behavioural therapy</li>
    <li><b>Medication:</b> Prescribed by doctors when needed</li>
    <li><b>Support groups:</b> Connecting with others</li>
    <li><b>Self-help:</b> Lifestyle changes and coping strategies</li>
</ul>

<hr/>

<h2>Healthy lifestyle for mental wellness</h2>

<h3>Physical activity:</h3>
<ul>
    <li>Exercise releases mood-boosting chemicals</li>
    <li>Aim for 30 minutes daily</li>
    <li>Walking, dancing, sports all help</li>
</ul>

<h3>Sleep:</h3>
<ul>
    <li>Aim for 7-8 hours per night</li>
    <li>Keep a regular sleep schedule</li>
    <li>Limit screens before bedtime</li>
</ul>

<h3>Social connection:</h3>
<ul>
    <li>Spend time with people you trust</li>
    <li>Share your feelings with others</li>
    <li>Help others – it improves your own wellbeing</li>
</ul>

<h3>Stress management:</h3>
<ul>
    <li>Practice deep breathing</li>
    <li>Spend time in nature</li>
    <li>Engage in activities you enjoy</li>
</ul>

<hr/>

<h2>Supporting others</h2>

<h3>Helpful things to say:</h3>
<ul>
    <li>"I'm here for you"</li>
    <li>"How can I help?"</li>
    <li>"You're not alone"</li>
    <li>"It's okay to not be okay"</li>
</ul>

<h3 class="warning">Things to avoid saying:</h3>
<ul>
    <li>"Just cheer up"</li>
    <li>"Others have it worse"</li>
    <li>"It's all in your head"</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/mental-health">www.who.int/news-room/fact-sheets/detail/mental-health</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mental Health Foundation</div>
        <a href="https://www.mentalhealth.org">www.mentalhealth.org</a>
    </div>
</div>
                """.trimIndent(),
                summary = "WHO fact sheet on mental health: 1 in 8 people lives with a mental disorder. Learn about symptoms, treatment, and how to support others.",
                source = "WHO",
                category = "Mental Health"
            )
        )
    }
}
