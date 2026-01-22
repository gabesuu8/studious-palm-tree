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

<h2>👁️ Visual Symptom Guide</h2>
<p><i>Learn to recognize these warning signs:</i></p>

<!-- JAUNDICE VISUAL GUIDE -->
<div style="background: linear-gradient(135deg, #FFF9C4 0%, #FFF59D 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #F9A825;">
    <h3 style="margin-top: 0; color: #F57F17;">🟡 Jaundice (Yellowing)</h3>
    <p style="margin-bottom: 8px;"><b>What to look for:</b></p>
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 12px; background: white; border-radius: 8px; margin-bottom: 8px;">
                    <div style="font-size: 40px;">👁️</div>
                    <div style="font-size: 12px; color: #666;">EYES</div>
                </div>
                <p style="font-size: 13px; margin: 0;"><b>Normal:</b> White part is clear white</p>
                <p style="font-size: 13px; margin: 4px 0 0 0; color: #E65100;"><b>Jaundice:</b> White part turns yellow</p>
            </td>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 12px; background: white; border-radius: 8px; margin-bottom: 8px;">
                    <div style="font-size: 40px;">🖐️</div>
                    <div style="font-size: 12px; color: #666;">PALMS</div>
                </div>
                <p style="font-size: 13px; margin: 0;"><b>Normal:</b> Pink or natural skin tone</p>
                <p style="font-size: 13px; margin: 4px 0 0 0; color: #E65100;"><b>Jaundice:</b> Yellowish tint on palms</p>
            </td>
        </tr>
    </table>
    <p style="font-size: 12px; color: #666; margin: 8px 0 0 0; font-style: italic;">💡 Check eyes and palms in natural daylight for best visibility</p>
</div>

<!-- DEHYDRATION VISUAL GUIDE -->
<div style="background: linear-gradient(135deg, #E3F2FD 0%, #BBDEFB 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #1976D2;">
    <h3 style="margin-top: 0; color: #0D47A1;">💧 Dehydration Signs</h3>
    <p style="margin-bottom: 12px;"><b>Check these areas:</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">👄</span>
            <div>
                <b>Mouth & Lips</b><br/>
                <span style="font-size: 13px;">Dry, cracked lips • Sticky or dry mouth • Thick saliva</span>
            </div>
        </div>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">👁️</span>
            <div>
                <b>Eyes</b><br/>
                <span style="font-size: 13px;">Sunken appearance • No tears when crying (in children) • Dark circles</span>
            </div>
        </div>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">🖐️</span>
            <div>
                <b>Skin Pinch Test</b><br/>
                <span style="font-size: 13px;">Pinch skin on back of hand → If it stays "tented" for >2 seconds = dehydration</span>
            </div>
        </div>
    </div>
    
    <div style="background: #FFECB3; border-radius: 8px; padding: 12px;">
        <b>🚽 Urine Check:</b>
        <table style="width: 100%; margin-top: 8px; font-size: 13px;">
            <tr>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FFFDE7; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Pale yellow = Good
                </td>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FFD54F; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Dark yellow = Drink more
                </td>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FF8F00; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Orange/Brown = Urgent!
                </td>
            </tr>
        </table>
    </div>
</div>

<!-- BREATHING DIFFICULTY VISUAL GUIDE -->
<div style="background: linear-gradient(135deg, #FFEBEE 0%, #FFCDD2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #D32F2F;">
    <h3 style="margin-top: 0; color: #B71C1C;">🫁 Breathing Difficulty Signs</h3>
    <p style="margin-bottom: 12px;"><b>Watch for these warning signs:</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 8px;">
        <table style="width: 100%; border-collapse: collapse;">
            <tr>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😮‍💨</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Fast Breathing</div>
                    <div style="font-size: 11px; color: #666;">Rapid, shallow breaths<br/>Can't catch breath</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😰</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Nostril Flaring</div>
                    <div style="font-size: 11px; color: #666;">Nostrils open wide<br/>with each breath</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😣</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Chest Pulling</div>
                    <div style="font-size: 11px; color: #666;">Skin sinks between<br/>ribs when breathing</div>
                </td>
            </tr>
        </table>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px;">
        <b>📊 Normal Breathing Rates:</b>
        <table style="width: 100%; margin-top: 8px; font-size: 13px; border-collapse: collapse;">
            <tr style="background: #f5f5f5;">
                <td style="padding: 6px; border: 1px solid #ddd;"><b>Age</b></td>
                <td style="padding: 6px; border: 1px solid #ddd;"><b>Normal (breaths/min)</b></td>
                <td style="padding: 6px; border: 1px solid #ddd;"><b>⚠️ Danger</b></td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Baby (0-1 yr)</td>
                <td style="padding: 6px; border: 1px solid #ddd;">30-60</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;60</td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Child (1-5 yrs)</td>
                <td style="padding: 6px; border: 1px solid #ddd;">20-40</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;40</td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Adult</td>
                <td style="padding: 6px; border: 1px solid #ddd;">12-20</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;30</td>
            </tr>
        </table>
        <p style="font-size: 12px; color: #666; margin: 8px 0 0 0;">💡 Count breaths for 60 seconds while person is calm/resting</p>
    </div>
</div>

<!-- SKIN CHANGES / PALLOR GUIDE -->
<div style="background: linear-gradient(135deg, #F3E5F5 0%, #E1BEE7 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #7B1FA2;">
    <h3 style="margin-top: 0; color: #4A148C;">🩺 Skin Changes & Pallor</h3>
    <p style="margin-bottom: 12px;"><b>Signs of anemia (low blood) from malaria:</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px;">
        <table style="width: 100%; border-collapse: collapse;">
            <tr>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">👅</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Tongue & Gums</div>
                    <div style="font-size: 11px; color: #666;">Pale pink or white<br/>instead of healthy red</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">💅</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Nail Beds</div>
                    <div style="font-size: 11px; color: #666;">Press nail → Color<br/>slow to return</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">👁️</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Inner Eyelid</div>
                    <div style="font-size: 11px; color: #666;">Pull down lower lid<br/>Should be pink/red</div>
                </td>
            </tr>
        </table>
    </div>
    <p style="font-size: 12px; color: #666; margin: 8px 0 0 0; font-style: italic;">💡 Pallor check works for all skin tones - check areas listed above</p>
</div>

<!-- FEVER PATTERN GUIDE -->
<div style="background: linear-gradient(135deg, #FFF3E0 0%, #FFE0B2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #E65100;">
    <h3 style="margin-top: 0; color: #BF360C;">🌡️ Fever Pattern in Malaria</h3>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <p style="margin: 0 0 8px 0;"><b>Typical malaria fever cycle:</b></p>
        <div style="display: flex; align-items: center; justify-content: space-between; padding: 8px 0;">
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">🥶</div>
                <div style="font-size: 11px;"><b>Cold Stage</b><br/>Shivering, chills<br/>(15-60 min)</div>
            </div>
            <div style="font-size: 20px;">→</div>
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">🥵</div>
                <div style="font-size: 11px;"><b>Hot Stage</b><br/>High fever, headache<br/>(2-6 hours)</div>
            </div>
            <div style="font-size: 20px;">→</div>
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">😓</div>
                <div style="font-size: 11px;"><b>Sweating Stage</b><br/>Fever breaks, sweats<br/>(2-4 hours)</div>
            </div>
        </div>
    </div>
    
    <p style="font-size: 13px; background: #FFF8E1; padding: 8px; border-radius: 4px; margin: 0;">
        <b>⚠️ Danger:</b> Temperature above <b>39.5°C (103°F)</b> = Seek immediate medical care
    </p>
</div>

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
                summary = "WHO fact sheet on malaria: 282 million cases globally in 2024. Includes visual symptom guides for jaundice, dehydration, and breathing difficulty.",
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
            ),
            
            // ANTIMALARIALS - English
            Article(
                title = "Antimalarial Medications",
                content = """
<div class="highlight-box">
    <b>⚠️ IMPORTANT DISCLAIMER</b><br/>
    This information is for <b>educational purposes only</b>. It does not replace professional medical advice. Always consult a healthcare provider before taking any medication. Do not self-diagnose or self-treat malaria.
</div>

<div class="key-facts">
    <h2>Overview</h2>
    <ul>
        <li>Antimalarials are medications used to prevent and treat malaria.</li>
        <li>The choice of medication depends on the type of malaria and local drug resistance patterns.</li>
        <li>Always complete the full course of treatment.</li>
    </ul>
</div>

<hr/>

<h2>Artemether-Lumefantrine (Coartem®/AL)</h2>
<p><b>Purpose:</b> First-line treatment for uncomplicated P. falciparum malaria. Most commonly used ACT in Africa.</p>

<h3>Dosage (by weight):</h3>
<ul>
    <li><b>5-14 kg:</b> 1 tablet per dose</li>
    <li><b>15-24 kg:</b> 2 tablets per dose</li>
    <li><b>25-34 kg:</b> 3 tablets per dose</li>
    <li><b>≥35 kg (Adult):</b> 4 tablets per dose</li>
</ul>
<p><b>Schedule:</b> 6 doses over 3 days (at 0, 8, 24, 36, 48, and 60 hours)</p>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Take with food or milk (fatty food improves absorption)</li>
    <li>Do not use in first trimester of pregnancy</li>
    <li>May cause dizziness – avoid driving</li>
    <li>Do not take with grapefruit juice</li>
</ul>

<hr/>

<h2>Artesunate-Amodiaquine (ASAQ)</h2>
<p><b>Purpose:</b> Alternative ACT for uncomplicated malaria treatment.</p>

<h3>Dosage (once daily for 3 days):</h3>
<ul>
    <li><b>4.5-8 kg:</b> 25mg/67.5mg tablet</li>
    <li><b>9-17 kg:</b> 50mg/135mg tablet</li>
    <li><b>18-35 kg:</b> 100mg/270mg tablet</li>
    <li><b>≥36 kg (Adult):</b> 100mg/270mg × 2 tablets</li>
</ul>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>May cause temporary itching (more common in dark-skinned individuals)</li>
    <li>Can cause nausea – take with food</li>
    <li>Avoid in patients with liver problems</li>
</ul>

<hr/>

<h2>Quinine</h2>
<p><b>Purpose:</b> Treatment of severe malaria; used when ACTs are not available or contraindicated.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 600mg (2 tablets) every 8 hours for 7 days</li>
    <li><b>Children:</b> 10mg/kg every 8 hours for 7 days</li>
</ul>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Can cause ringing in ears (tinnitus), dizziness, blurred vision</li>
    <li>Can cause low blood sugar – eat regularly</li>
    <li>Do not exceed recommended dose</li>
    <li>Not recommended for prevention</li>
</ul>

<hr/>

<h2>Sulfadoxine-Pyrimethamine (SP/Fansidar®)</h2>
<p><b>Purpose:</b> Intermittent preventive treatment in pregnancy (IPTp) and infants (IPTi). NOT for treatment due to widespread resistance.</p>

<h3>Dosage for IPTp:</h3>
<ul>
    <li><b>Pregnant women:</b> 3 tablets as single dose at each antenatal visit (starting 2nd trimester)</li>
</ul>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Do not use if allergic to sulfa drugs</li>
    <li>Not for treatment of acute malaria</li>
    <li>Avoid in first trimester and last 4 weeks of pregnancy</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">WHO Guidelines for Malaria Treatment</div>
        <a href="https://www.who.int/publications/i/item/guidelines-for-malaria">www.who.int/publications/guidelines-for-malaria</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO Essential Medicines List</div>
        <a href="https://www.who.int/groups/expert-committee-on-selection-and-use-of-essential-medicines/essential-medicines-lists">WHO Essential Medicines</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Reference guide for antimalarial medications: Coartem, ASAQ, Quinine, SP. Includes dosages for adults and children, and safety warnings.",
                source = "WHO",
                category = "Medications"
            ),
            
            // ANTIBIOTICS - English
            Article(
                title = "Common Antibiotics",
                content = """
<div class="highlight-box">
    <b>⚠️ IMPORTANT DISCLAIMER</b><br/>
    This information is for <b>educational purposes only</b>. Antibiotics require a prescription. Improper use contributes to antibiotic resistance. Never share antibiotics or use leftover medications. Always complete the full course as prescribed.
</div>

<div class="key-facts">
    <h2>Overview</h2>
    <ul>
        <li>Antibiotics treat bacterial infections only – they do NOT work against viruses.</li>
        <li>Antibiotic resistance is a growing global threat.</li>
        <li>Always complete the full course even if you feel better.</li>
    </ul>
</div>

<hr/>

<h2>Amoxicillin</h2>
<p><b>Purpose:</b> Respiratory infections, ear infections, urinary tract infections, skin infections.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 250-500mg every 8 hours, or 500-875mg every 12 hours</li>
    <li><b>Children:</b> 25-50mg/kg/day divided into 2-3 doses</li>
</ul>
<p><b>Duration:</b> Usually 5-10 days depending on infection</p>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Do NOT take if allergic to penicillin</li>
    <li>May cause diarrhea, nausea, rash</li>
    <li>Can reduce effectiveness of birth control pills</li>
    <li>Take with or without food</li>
</ul>

<hr/>

<h2>Metronidazole (Flagyl®)</h2>
<p><b>Purpose:</b> Amoebic dysentery, giardia, bacterial vaginosis, dental infections, some stomach infections.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 400-500mg every 8 hours</li>
    <li><b>Children:</b> 7.5mg/kg every 8 hours</li>
</ul>
<p><b>Duration:</b> 5-10 days depending on infection</p>

<h3>⚠️ Warnings:</h3>
<ul>
    <li class="warning"><b>Do NOT drink alcohol</b> – causes severe nausea and vomiting</li>
    <li>Avoid alcohol for 48 hours after completing treatment</li>
    <li>May cause metallic taste in mouth</li>
    <li>Take with food to reduce stomach upset</li>
    <li>May darken urine (harmless)</li>
</ul>

<hr/>

<h2>Ciprofloxacin</h2>
<p><b>Purpose:</b> Urinary tract infections, typhoid fever, severe diarrhea (bacterial), bone infections.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 250-750mg every 12 hours</li>
    <li><b>Children:</b> Generally avoided in children; if necessary, 10-20mg/kg/day</li>
</ul>
<p><b>Duration:</b> 3-14 days depending on infection</p>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Not recommended for children/adolescents (may affect bone growth)</li>
    <li>Not for pregnant or breastfeeding women</li>
    <li>Can cause tendon problems – stop if joint pain occurs</li>
    <li>Avoid dairy products and antacids (take 2 hours apart)</li>
    <li>May cause sun sensitivity – use sunscreen</li>
</ul>

<hr/>

<h2>Cotrimoxazole (Septrin®/Bactrim®)</h2>
<p><b>Purpose:</b> Respiratory infections, urinary tract infections, ear infections, prevention of opportunistic infections in HIV patients.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 960mg (double-strength tablet) every 12 hours</li>
    <li><b>Children:</b> 24mg/kg/day divided into 2 doses</li>
    <li><b>HIV prophylaxis (adult):</b> 960mg once daily</li>
</ul>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>Do NOT take if allergic to sulfa drugs</li>
    <li>Drink plenty of water</li>
    <li>May cause skin rash – stop immediately if rash appears</li>
    <li>Can cause sun sensitivity</li>
    <li>Not for late pregnancy or newborns</li>
</ul>

<hr/>

<h2>Doxycycline</h2>
<p><b>Purpose:</b> Respiratory infections, malaria prevention, cholera, sexually transmitted infections.</p>

<h3>Dosage:</h3>
<ul>
    <li><b>Adults:</b> 100mg every 12 hours or 200mg once daily</li>
    <li><b>Children (>8 years):</b> 2-4mg/kg/day in 1-2 doses</li>
</ul>

<h3>⚠️ Warnings:</h3>
<ul>
    <li>NOT for children under 8 years (affects teeth development)</li>
    <li>NOT for pregnant or breastfeeding women</li>
    <li>Causes severe sun sensitivity – use strong sunscreen</li>
    <li>Take with plenty of water; do not lie down for 30 minutes after</li>
    <li>Avoid dairy products near dosing time</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">WHO Model Formulary</div>
        <a href="https://www.who.int/publications/i/item/9789241547659">WHO Model Formulary</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO AWaRe Antibiotic Classification</div>
        <a href="https://www.who.int/publications/i/item/WHO-MHP-HPS-EML-2021.04">WHO AWaRe Classification</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Reference guide for common antibiotics: Amoxicillin, Metronidazole, Ciprofloxacin, Cotrimoxazole, Doxycycline. Dosages and safety warnings.",
                source = "WHO",
                category = "Medications"
            ),
            
            // ORS - English
            Article(
                title = "Oral Rehydration Salts (ORS)",
                content = """
<div class="highlight-box">
    <b>⚠️ IMPORTANT DISCLAIMER</b><br/>
    This information is for <b>educational purposes only</b>. Severe dehydration is a medical emergency requiring professional care. ORS treats dehydration, not the underlying cause of diarrhea.
</div>

<div class="key-facts">
    <h2>Overview</h2>
    <ul>
        <li>ORS is the most effective treatment for dehydration from diarrhea.</li>
        <li>ORS saves millions of lives every year, especially children.</li>
        <li>ORS replaces fluids AND essential salts lost during diarrhea.</li>
        <li>ORS does NOT stop diarrhea but prevents death from dehydration.</li>
    </ul>
</div>

<hr/>

<h2>What is ORS?</h2>
<p>Oral Rehydration Salts is a precise mixture of:</p>
<ul>
    <li>Glucose (sugar)</li>
    <li>Sodium chloride (salt)</li>
    <li>Potassium chloride</li>
    <li>Trisodium citrate or sodium bicarbonate</li>
</ul>

<hr/>

<h2>When to Use ORS</h2>
<ul>
    <li>Diarrhea (3 or more loose stools per day)</li>
    <li>Vomiting</li>
    <li>Cholera</li>
    <li>Any condition causing fluid loss</li>
</ul>

<hr/>

<h2>How to Prepare ORS</h2>
<h3>Using ORS Packets:</h3>
<ol>
    <li>Wash hands with soap and water</li>
    <li>Pour entire packet into 1 litre of clean (boiled and cooled) water</li>
    <li>Stir until completely dissolved</li>
    <li>Use within 24 hours; discard if not used</li>
</ol>

<div class="highlight-box">
    <b>🏠 Homemade ORS (Emergency only):</b><br/>
    If packets unavailable, mix in 1 litre of clean water:<br/>
    • <b>6 level teaspoons</b> of sugar<br/>
    • <b>½ level teaspoon</b> of salt<br/><br/>
    <i>Note: Commercial ORS packets are preferred as they contain the correct balance of salts.</i>
</div>

<hr/>

<h2>Dosage Guidelines</h2>

<h3>For Children:</h3>
<ul>
    <li><b>Under 2 years:</b> 50-100ml after each loose stool</li>
    <li><b>2-10 years:</b> 100-200ml after each loose stool</li>
    <li><b>Over 10 years:</b> As much as wanted</li>
</ul>

<h3>For Adults:</h3>
<ul>
    <li>200-400ml after each loose stool</li>
    <li>Drink as much as tolerated</li>
    <li>Typically 2-3 litres per day during acute diarrhea</li>
</ul>

<h3>For Severe Dehydration:</h3>
<ul>
    <li><b>Children:</b> 75ml/kg over 4 hours</li>
    <li><b>Adults:</b> 750ml-1 litre per hour initially</li>
</ul>

<hr/>

<h2>⚠️ Important Warnings</h2>
<ul>
    <li>Do NOT add extra sugar or salt – follow instructions exactly</li>
    <li>Do NOT use fruit juice, soft drinks, or sports drinks as substitutes</li>
    <li>Continue breastfeeding infants alongside ORS</li>
    <li>Continue eating if able – do not fast</li>
</ul>

<h3 class="warning">Seek Emergency Care If:</h3>
<ul>
    <li>Unable to drink or keep fluids down</li>
    <li>Very sunken eyes</li>
    <li>Lethargy or unconsciousness</li>
    <li>No urine for 6+ hours</li>
    <li>Blood in stool</li>
    <li>High fever with diarrhea</li>
</ul>

<hr/>

<h2>Zinc Supplementation</h2>
<p>WHO recommends zinc supplements with ORS for children under 5:</p>
<ul>
    <li><b>Under 6 months:</b> 10mg zinc daily for 10-14 days</li>
    <li><b>6 months - 5 years:</b> 20mg zinc daily for 10-14 days</li>
</ul>
<p>Zinc reduces duration and severity of diarrhea and prevents future episodes.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">WHO/UNICEF Joint Statement on ORS</div>
        <a href="https://www.who.int/publications/i/item/WHO-FCH-CAH-06.1">WHO ORS Guidelines</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO Treatment of Diarrhoea Manual</div>
        <a href="https://www.who.int/publications/i/item/9241593180">WHO Diarrhoea Treatment</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Complete guide to Oral Rehydration Salts (ORS): preparation, dosage for children and adults, zinc supplementation, and warning signs.",
                source = "WHO, UNICEF",
                category = "Medications"
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

<h2>👁️ Guide visuel des symptômes</h2>
<p><i>Apprenez à reconnaître ces signes d'alerte :</i></p>

<!-- GUIDE VISUEL JAUNISSE -->
<div style="background: linear-gradient(135deg, #FFF9C4 0%, #FFF59D 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #F9A825;">
    <h3 style="margin-top: 0; color: #F57F17;">🟡 Jaunisse (Ictère)</h3>
    <p style="margin-bottom: 8px;"><b>Ce qu'il faut observer :</b></p>
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 12px; background: white; border-radius: 8px; margin-bottom: 8px;">
                    <div style="font-size: 40px;">👁️</div>
                    <div style="font-size: 12px; color: #666;">YEUX</div>
                </div>
                <p style="font-size: 13px; margin: 0;"><b>Normal :</b> Le blanc est bien blanc</p>
                <p style="font-size: 13px; margin: 4px 0 0 0; color: #E65100;"><b>Jaunisse :</b> Le blanc devient jaune</p>
            </td>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 12px; background: white; border-radius: 8px; margin-bottom: 8px;">
                    <div style="font-size: 40px;">🖐️</div>
                    <div style="font-size: 12px; color: #666;">PAUMES</div>
                </div>
                <p style="font-size: 13px; margin: 0;"><b>Normal :</b> Rose ou teint naturel</p>
                <p style="font-size: 13px; margin: 4px 0 0 0; color: #E65100;"><b>Jaunisse :</b> Teinte jaunâtre sur les paumes</p>
            </td>
        </tr>
    </table>
    <p style="font-size: 12px; color: #666; margin: 8px 0 0 0; font-style: italic;">💡 Vérifiez les yeux et les paumes à la lumière naturelle pour une meilleure visibilité</p>
</div>

<!-- GUIDE VISUEL DÉSHYDRATATION -->
<div style="background: linear-gradient(135deg, #E3F2FD 0%, #BBDEFB 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #1976D2;">
    <h3 style="margin-top: 0; color: #0D47A1;">💧 Signes de déshydratation</h3>
    <p style="margin-bottom: 12px;"><b>Vérifiez ces zones :</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">👄</span>
            <div>
                <b>Bouche et lèvres</b><br/>
                <span style="font-size: 13px;">Lèvres sèches et craquelées • Bouche sèche ou collante • Salive épaisse</span>
            </div>
        </div>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">👁️</span>
            <div>
                <b>Yeux</b><br/>
                <span style="font-size: 13px;">Apparence enfoncée • Pas de larmes en pleurant (chez les enfants) • Cernes</span>
            </div>
        </div>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <div style="display: flex; align-items: center; margin-bottom: 8px;">
            <span style="font-size: 28px; margin-right: 12px;">🖐️</span>
            <div>
                <b>Test du pli cutané</b><br/>
                <span style="font-size: 13px;">Pincez la peau du dos de la main → Si elle reste "pliée" >2 secondes = déshydratation</span>
            </div>
        </div>
    </div>
    
    <div style="background: #FFECB3; border-radius: 8px; padding: 12px;">
        <b>🚽 Vérification de l'urine :</b>
        <table style="width: 100%; margin-top: 8px; font-size: 13px;">
            <tr>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FFFDE7; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Jaune pâle = Bien
                </td>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FFD54F; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Jaune foncé = Boire plus
                </td>
                <td style="padding: 4px;">
                    <span style="display: inline-block; width: 20px; height: 20px; background: #FF8F00; border: 1px solid #ddd; border-radius: 4px; vertical-align: middle;"></span>
                    Orange/Brun = Urgent !
                </td>
            </tr>
        </table>
    </div>
</div>

<!-- GUIDE VISUEL DIFFICULTÉS RESPIRATOIRES -->
<div style="background: linear-gradient(135deg, #FFEBEE 0%, #FFCDD2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #D32F2F;">
    <h3 style="margin-top: 0; color: #B71C1C;">🫁 Signes de difficultés respiratoires</h3>
    <p style="margin-bottom: 12px;"><b>Surveillez ces signes d'alerte :</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 8px;">
        <table style="width: 100%; border-collapse: collapse;">
            <tr>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😮‍💨</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Respiration rapide</div>
                    <div style="font-size: 11px; color: #666;">Respirations rapides<br/>et superficielles</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😰</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Battement des ailes du nez</div>
                    <div style="font-size: 11px; color: #666;">Narines s'ouvrent<br/>à chaque respiration</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">😣</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Tirage intercostal</div>
                    <div style="font-size: 11px; color: #666;">La peau se creuse entre<br/>les côtes à la respiration</div>
                </td>
            </tr>
        </table>
    </div>
    
    <div style="background: white; border-radius: 8px; padding: 12px;">
        <b>📊 Fréquences respiratoires normales :</b>
        <table style="width: 100%; margin-top: 8px; font-size: 13px; border-collapse: collapse;">
            <tr style="background: #f5f5f5;">
                <td style="padding: 6px; border: 1px solid #ddd;"><b>Âge</b></td>
                <td style="padding: 6px; border: 1px solid #ddd;"><b>Normal (resp/min)</b></td>
                <td style="padding: 6px; border: 1px solid #ddd;"><b>⚠️ Danger</b></td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Bébé (0-1 an)</td>
                <td style="padding: 6px; border: 1px solid #ddd;">30-60</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;60</td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Enfant (1-5 ans)</td>
                <td style="padding: 6px; border: 1px solid #ddd;">20-40</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;40</td>
            </tr>
            <tr>
                <td style="padding: 6px; border: 1px solid #ddd;">Adulte</td>
                <td style="padding: 6px; border: 1px solid #ddd;">12-20</td>
                <td style="padding: 6px; border: 1px solid #ddd; color: #D32F2F;">&gt;30</td>
            </tr>
        </table>
        <p style="font-size: 12px; color: #666; margin: 8px 0 0 0;">💡 Comptez les respirations pendant 60 secondes au repos</p>
    </div>
</div>

<!-- GUIDE CHANGEMENTS CUTANÉS / PÂLEUR -->
<div style="background: linear-gradient(135deg, #F3E5F5 0%, #E1BEE7 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #7B1FA2;">
    <h3 style="margin-top: 0; color: #4A148C;">🩺 Changements cutanés et pâleur</h3>
    <p style="margin-bottom: 12px;"><b>Signes d'anémie (manque de sang) due au paludisme :</b></p>
    
    <div style="background: white; border-radius: 8px; padding: 12px;">
        <table style="width: 100%; border-collapse: collapse;">
            <tr>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">👅</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Langue et gencives</div>
                    <div style="font-size: 11px; color: #666;">Rose pâle ou blanc<br/>au lieu de rouge sain</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">💅</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Lit des ongles</div>
                    <div style="font-size: 11px; color: #666;">Appuyez sur l'ongle →<br/>Couleur lente à revenir</div>
                </td>
                <td style="padding: 8px; text-align: center; vertical-align: top; width: 33%;">
                    <div style="font-size: 36px;">👁️</div>
                    <div style="font-size: 12px; font-weight: bold; margin: 4px 0;">Intérieur de la paupière</div>
                    <div style="font-size: 11px; color: #666;">Tirez la paupière inférieure<br/>Devrait être rose/rouge</div>
                </td>
            </tr>
        </table>
    </div>
    <p style="font-size: 12px; color: #666; margin: 8px 0 0 0; font-style: italic;">💡 La vérification de la pâleur fonctionne pour toutes les couleurs de peau</p>
</div>

<!-- GUIDE PATTERN DE FIÈVRE -->
<div style="background: linear-gradient(135deg, #FFF3E0 0%, #FFE0B2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #E65100;">
    <h3 style="margin-top: 0; color: #BF360C;">🌡️ Cycle de fièvre du paludisme</h3>
    
    <div style="background: white; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
        <p style="margin: 0 0 8px 0;"><b>Cycle typique de la fièvre paludéenne :</b></p>
        <div style="display: flex; align-items: center; justify-content: space-between; padding: 8px 0;">
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">🥶</div>
                <div style="font-size: 11px;"><b>Stade froid</b><br/>Frissons, tremblements<br/>(15-60 min)</div>
            </div>
            <div style="font-size: 20px;">→</div>
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">🥵</div>
                <div style="font-size: 11px;"><b>Stade chaud</b><br/>Forte fièvre, maux de tête<br/>(2-6 heures)</div>
            </div>
            <div style="font-size: 20px;">→</div>
            <div style="text-align: center; flex: 1;">
                <div style="font-size: 24px;">😓</div>
                <div style="font-size: 11px;"><b>Stade de sueur</b><br/>Fièvre tombe, sueurs<br/>(2-4 heures)</div>
            </div>
        </div>
    </div>
    
    <p style="font-size: 13px; background: #FFF8E1; padding: 8px; border-radius: 4px; margin: 0;">
        <b>⚠️ Danger :</b> Température supérieure à <b>39,5°C (103°F)</b> = Consultez immédiatement
    </p>
</div>

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
                summary = "Fiche OMS sur le paludisme : 282 millions de cas en 2024. Inclut des guides visuels pour la jaunisse, la déshydratation et les difficultés respiratoires.",
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
            ),
            
            // ANTIPALUDÉENS - French
            Article(
                title = "Médicaments antipaludiques",
                content = """
<div class="highlight-box">
    <b>⚠️ AVERTISSEMENT IMPORTANT</b><br/>
    Ces informations sont <b>à titre éducatif uniquement</b>. Elles ne remplacent pas les conseils médicaux professionnels. Consultez toujours un professionnel de santé avant de prendre tout médicament. Ne vous auto-diagnostiquez pas et ne traitez pas le paludisme vous-même.
</div>

<div class="key-facts">
    <h2>Aperçu</h2>
    <ul>
        <li>Les antipaludiques sont des médicaments utilisés pour prévenir et traiter le paludisme.</li>
        <li>Le choix du médicament dépend du type de paludisme et des résistances locales.</li>
        <li>Terminez toujours le traitement complet.</li>
    </ul>
</div>

<hr/>

<h2>Artéméther-Luméfantrine (Coartem®/AL)</h2>
<p><b>Usage :</b> Traitement de première intention du paludisme à P. falciparum non compliqué. CTA le plus utilisé en Afrique.</p>

<h3>Posologie (selon le poids) :</h3>
<ul>
    <li><b>5-14 kg :</b> 1 comprimé par prise</li>
    <li><b>15-24 kg :</b> 2 comprimés par prise</li>
    <li><b>25-34 kg :</b> 3 comprimés par prise</li>
    <li><b>≥35 kg (Adulte) :</b> 4 comprimés par prise</li>
</ul>
<p><b>Schéma :</b> 6 prises sur 3 jours (à 0, 8, 24, 36, 48 et 60 heures)</p>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>Prendre avec de la nourriture ou du lait (les graisses améliorent l'absorption)</li>
    <li>Ne pas utiliser au premier trimestre de grossesse</li>
    <li>Peut causer des vertiges – éviter de conduire</li>
    <li>Ne pas prendre avec du jus de pamplemousse</li>
</ul>

<hr/>

<h2>Artésunate-Amodiaquine (ASAQ)</h2>
<p><b>Usage :</b> CTA alternative pour le traitement du paludisme non compliqué.</p>

<h3>Posologie (une fois par jour pendant 3 jours) :</h3>
<ul>
    <li><b>4,5-8 kg :</b> comprimé 25mg/67,5mg</li>
    <li><b>9-17 kg :</b> comprimé 50mg/135mg</li>
    <li><b>18-35 kg :</b> comprimé 100mg/270mg</li>
    <li><b>≥36 kg (Adulte) :</b> 100mg/270mg × 2 comprimés</li>
</ul>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>Peut causer des démangeaisons temporaires (plus fréquent chez les personnes à peau foncée)</li>
    <li>Peut causer des nausées – prendre avec de la nourriture</li>
    <li>Éviter chez les patients ayant des problèmes hépatiques</li>
</ul>

<hr/>

<h2>Quinine</h2>
<p><b>Usage :</b> Traitement du paludisme grave ; utilisée quand les CTA ne sont pas disponibles ou contre-indiquées.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 600mg (2 comprimés) toutes les 8 heures pendant 7 jours</li>
    <li><b>Enfants :</b> 10mg/kg toutes les 8 heures pendant 7 jours</li>
</ul>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>Peut causer des bourdonnements d'oreilles (acouphènes), vertiges, vision floue</li>
    <li>Peut causer une hypoglycémie – manger régulièrement</li>
    <li>Ne pas dépasser la dose recommandée</li>
    <li>Non recommandée pour la prévention</li>
</ul>

<hr/>

<h2>Sulfadoxine-Pyriméthamine (SP/Fansidar®)</h2>
<p><b>Usage :</b> Traitement préventif intermittent pendant la grossesse (TPIg) et chez les nourrissons (TPIn). PAS pour le traitement en raison de la résistance répandue.</p>

<h3>Posologie pour le TPIg :</h3>
<ul>
    <li><b>Femmes enceintes :</b> 3 comprimés en dose unique à chaque visite prénatale (à partir du 2e trimestre)</li>
</ul>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>Ne pas utiliser en cas d'allergie aux sulfamides</li>
    <li>Pas pour le traitement du paludisme aigu</li>
    <li>Éviter au premier trimestre et dans les 4 dernières semaines de grossesse</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Directives OMS pour le traitement du paludisme</div>
        <a href="https://www.who.int/publications/i/item/guidelines-for-malaria">www.who.int/publications/guidelines-for-malaria</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Guide de référence des antipaludiques : Coartem, ASAQ, Quinine, SP. Posologies pour adultes et enfants, mises en garde.",
                source = "OMS",
                category = "Médicaments"
            ),
            
            // ANTIBIOTIQUES - French
            Article(
                title = "Antibiotiques courants",
                content = """
<div class="highlight-box">
    <b>⚠️ AVERTISSEMENT IMPORTANT</b><br/>
    Ces informations sont <b>à titre éducatif uniquement</b>. Les antibiotiques nécessitent une ordonnance. Une mauvaise utilisation contribue à la résistance aux antibiotiques. Ne partagez jamais les antibiotiques et n'utilisez pas de médicaments restants. Terminez toujours le traitement complet prescrit.
</div>

<div class="key-facts">
    <h2>Aperçu</h2>
    <ul>
        <li>Les antibiotiques traitent uniquement les infections bactériennes – ils NE fonctionnent PAS contre les virus.</li>
        <li>La résistance aux antibiotiques est une menace mondiale croissante.</li>
        <li>Terminez toujours le traitement complet même si vous vous sentez mieux.</li>
    </ul>
</div>

<hr/>

<h2>Amoxicilline</h2>
<p><b>Usage :</b> Infections respiratoires, otites, infections urinaires, infections cutanées.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 250-500mg toutes les 8 heures, ou 500-875mg toutes les 12 heures</li>
    <li><b>Enfants :</b> 25-50mg/kg/jour divisés en 2-3 prises</li>
</ul>
<p><b>Durée :</b> Généralement 5-10 jours selon l'infection</p>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>NE PAS prendre en cas d'allergie à la pénicilline</li>
    <li>Peut causer diarrhée, nausées, éruption cutanée</li>
    <li>Peut réduire l'efficacité des pilules contraceptives</li>
    <li>Prendre avec ou sans nourriture</li>
</ul>

<hr/>

<h2>Métronidazole (Flagyl®)</h2>
<p><b>Usage :</b> Dysenterie amibienne, giardiase, vaginose bactérienne, infections dentaires, certaines infections gastriques.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 400-500mg toutes les 8 heures</li>
    <li><b>Enfants :</b> 7,5mg/kg toutes les 8 heures</li>
</ul>
<p><b>Durée :</b> 5-10 jours selon l'infection</p>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li class="warning"><b>NE PAS boire d'alcool</b> – provoque nausées et vomissements sévères</li>
    <li>Éviter l'alcool pendant 48 heures après la fin du traitement</li>
    <li>Peut causer un goût métallique dans la bouche</li>
    <li>Prendre avec de la nourriture pour réduire les maux d'estomac</li>
    <li>Peut foncer les urines (sans danger)</li>
</ul>

<hr/>

<h2>Ciprofloxacine</h2>
<p><b>Usage :</b> Infections urinaires, fièvre typhoïde, diarrhée sévère (bactérienne), infections osseuses.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 250-750mg toutes les 12 heures</li>
    <li><b>Enfants :</b> Généralement évité chez les enfants ; si nécessaire, 10-20mg/kg/jour</li>
</ul>
<p><b>Durée :</b> 3-14 jours selon l'infection</p>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>Non recommandé pour les enfants/adolescents (peut affecter la croissance osseuse)</li>
    <li>Non recommandé pour les femmes enceintes ou allaitantes</li>
    <li>Peut causer des problèmes tendineux – arrêter en cas de douleur articulaire</li>
    <li>Éviter les produits laitiers et antiacides (prendre à 2 heures d'intervalle)</li>
    <li>Peut causer une sensibilité au soleil – utiliser un écran solaire</li>
</ul>

<hr/>

<h2>Cotrimoxazole (Bactrim®)</h2>
<p><b>Usage :</b> Infections respiratoires, infections urinaires, otites, prévention des infections opportunistes chez les patients VIH.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 960mg (comprimé forte dose) toutes les 12 heures</li>
    <li><b>Enfants :</b> 24mg/kg/jour divisés en 2 prises</li>
    <li><b>Prophylaxie VIH (adulte) :</b> 960mg une fois par jour</li>
</ul>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>NE PAS prendre en cas d'allergie aux sulfamides</li>
    <li>Boire beaucoup d'eau</li>
    <li>Peut causer une éruption cutanée – arrêter immédiatement si éruption</li>
    <li>Peut causer une sensibilité au soleil</li>
    <li>Non recommandé en fin de grossesse ou chez les nouveau-nés</li>
</ul>

<hr/>

<h2>Doxycycline</h2>
<p><b>Usage :</b> Infections respiratoires, prévention du paludisme, choléra, infections sexuellement transmissibles.</p>

<h3>Posologie :</h3>
<ul>
    <li><b>Adultes :</b> 100mg toutes les 12 heures ou 200mg une fois par jour</li>
    <li><b>Enfants (>8 ans) :</b> 2-4mg/kg/jour en 1-2 prises</li>
</ul>

<h3>⚠️ Mises en garde :</h3>
<ul>
    <li>NON pour les enfants de moins de 8 ans (affecte le développement des dents)</li>
    <li>NON pour les femmes enceintes ou allaitantes</li>
    <li>Provoque une forte sensibilité au soleil – utiliser un écran solaire puissant</li>
    <li>Prendre avec beaucoup d'eau ; ne pas s'allonger pendant 30 minutes après</li>
    <li>Éviter les produits laitiers au moment de la prise</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Formulaire modèle de l'OMS</div>
        <a href="https://www.who.int/publications/i/item/9789241547659">Formulaire modèle OMS</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Guide de référence des antibiotiques courants : Amoxicilline, Métronidazole, Ciprofloxacine, Cotrimoxazole, Doxycycline. Posologies et mises en garde.",
                source = "OMS",
                category = "Médicaments"
            ),
            
            // SRO - French
            Article(
                title = "Sels de réhydratation orale (SRO)",
                content = """
<div class="highlight-box">
    <b>⚠️ AVERTISSEMENT IMPORTANT</b><br/>
    Ces informations sont <b>à titre éducatif uniquement</b>. La déshydratation sévère est une urgence médicale nécessitant des soins professionnels. La SRO traite la déshydratation, pas la cause sous-jacente de la diarrhée.
</div>

<div class="key-facts">
    <h2>Aperçu</h2>
    <ul>
        <li>La SRO est le traitement le plus efficace contre la déshydratation due à la diarrhée.</li>
        <li>La SRO sauve des millions de vies chaque année, surtout des enfants.</li>
        <li>La SRO remplace les liquides ET les sels essentiels perdus pendant la diarrhée.</li>
        <li>La SRO N'arrête PAS la diarrhée mais prévient la mort par déshydratation.</li>
    </ul>
</div>

<hr/>

<h2>Qu'est-ce que la SRO ?</h2>
<p>Les Sels de réhydratation orale sont un mélange précis de :</p>
<ul>
    <li>Glucose (sucre)</li>
    <li>Chlorure de sodium (sel)</li>
    <li>Chlorure de potassium</li>
    <li>Citrate trisodique ou bicarbonate de sodium</li>
</ul>

<hr/>

<h2>Quand utiliser la SRO</h2>
<ul>
    <li>Diarrhée (3 selles molles ou plus par jour)</li>
    <li>Vomissements</li>
    <li>Choléra</li>
    <li>Toute condition causant une perte de liquides</li>
</ul>

<hr/>

<h2>Comment préparer la SRO</h2>
<h3>Avec les sachets de SRO :</h3>
<ol>
    <li>Se laver les mains avec du savon et de l'eau</li>
    <li>Verser tout le sachet dans 1 litre d'eau propre (bouillie et refroidie)</li>
    <li>Remuer jusqu'à dissolution complète</li>
    <li>Utiliser dans les 24 heures ; jeter si non utilisée</li>
</ol>

<div class="highlight-box">
    <b>🏠 SRO maison (Urgence uniquement) :</b><br/>
    Si les sachets ne sont pas disponibles, mélanger dans 1 litre d'eau propre :<br/>
    • <b>6 cuillères à café rases</b> de sucre<br/>
    • <b>½ cuillère à café rase</b> de sel<br/><br/>
    <i>Note : Les sachets de SRO commerciaux sont préférables car ils contiennent le bon équilibre de sels.</i>
</div>

<hr/>

<h2>Guide de posologie</h2>

<h3>Pour les enfants :</h3>
<ul>
    <li><b>Moins de 2 ans :</b> 50-100ml après chaque selle liquide</li>
    <li><b>2-10 ans :</b> 100-200ml après chaque selle liquide</li>
    <li><b>Plus de 10 ans :</b> Autant que désiré</li>
</ul>

<h3>Pour les adultes :</h3>
<ul>
    <li>200-400ml après chaque selle liquide</li>
    <li>Boire autant que toléré</li>
    <li>Généralement 2-3 litres par jour pendant la diarrhée aiguë</li>
</ul>

<h3>Pour la déshydratation sévère :</h3>
<ul>
    <li><b>Enfants :</b> 75ml/kg sur 4 heures</li>
    <li><b>Adultes :</b> 750ml-1 litre par heure initialement</li>
</ul>

<hr/>

<h2>⚠️ Mises en garde importantes</h2>
<ul>
    <li>NE PAS ajouter de sucre ou de sel supplémentaire – suivre exactement les instructions</li>
    <li>NE PAS utiliser de jus de fruits, boissons gazeuses ou boissons sportives comme substituts</li>
    <li>Continuer l'allaitement des nourrissons en parallèle de la SRO</li>
    <li>Continuer à manger si possible – ne pas jeûner</li>
</ul>

<h3 class="warning">Consulter en urgence si :</h3>
<ul>
    <li>Incapacité de boire ou de garder les liquides</li>
    <li>Yeux très enfoncés</li>
    <li>Léthargie ou inconscience</li>
    <li>Pas d'urine depuis plus de 6 heures</li>
    <li>Sang dans les selles</li>
    <li>Forte fièvre avec diarrhée</li>
</ul>

<hr/>

<h2>Supplémentation en zinc</h2>
<p>L'OMS recommande des suppléments de zinc avec la SRO pour les enfants de moins de 5 ans :</p>
<ul>
    <li><b>Moins de 6 mois :</b> 10mg de zinc par jour pendant 10-14 jours</li>
    <li><b>6 mois - 5 ans :</b> 20mg de zinc par jour pendant 10-14 jours</li>
</ul>
<p>Le zinc réduit la durée et la sévérité de la diarrhée et prévient les épisodes futurs.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Déclaration conjointe OMS/UNICEF sur la SRO</div>
        <a href="https://www.who.int/publications/i/item/WHO-FCH-CAH-06.1">Directives OMS SRO</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Guide complet des Sels de réhydratation orale (SRO) : préparation, posologie pour enfants et adultes, supplémentation en zinc et signes d'alerte.",
                source = "OMS, UNICEF",
                category = "Médicaments"
            )
        )
    }
}
