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

<h2>👁️ Visual guide: Dehydration signs</h2>
<div style="background: linear-gradient(135deg, #E3F2FD 0%, #BBDEFB 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #1976D2;">
    <p style="margin-top: 0;"><b>Check these in a child with diarrhoea:</b></p>
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">👄</span><br/><b>Mouth & lips</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Dry, cracked lips = need more fluids</p>
            </td>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">👁️</span><br/><b>Eyes</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Sunken eyes = severe dehydration</p>
            </td>
        </tr>
        <tr>
            <td style="padding: 8px; vertical-align: top;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">🖐️</span><br/><b>Skin pinch</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Pinch skin on tummy – if it stays tented = dehydration</p>
            </td>
            <td style="padding: 8px; vertical-align: top;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">🚽</span><br/><b>Urine</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Dark yellow or no urine = drink more / seek care</p>
            </td>
        </tr>
    </table>
</div>

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
            
            // TUBERCULOSIS (TB) - English
            Article(
                title = "Tuberculosis (TB)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>TB is one of the top 10 causes of death worldwide and the leading cause from a single infectious agent.</li>
        <li>Africa accounts for about 25% of global TB cases, with high rates of TB-HIV co-infection.</li>
        <li>TB is curable with proper treatment (usually 6 months of antibiotics).</li>
        <li>TB spreads through the air when an infected person coughs, sneezes, or speaks.</li>
        <li>Not everyone infected with TB bacteria becomes sick; latent TB can be treated to prevent active disease.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Tuberculosis (TB) is caused by bacteria that most often affect the lungs. It spreads through the air when people with active TB cough, sneeze, or spit. TB is preventable and curable, but requires proper diagnosis and complete treatment.</p>

<h2>Symptoms</h2>
<h3>Active TB disease:</h3>
<ul>
    <li>Cough lasting 3 weeks or more (sometimes with blood)</li>
    <li>Chest pain</li>
    <li>Weakness or fatigue</li>
    <li>Weight loss</li>
    <li>Fever</li>
    <li>Night sweats</li>
</ul>

<h2>Who is at higher risk?</h2>
<ul>
    <li>People with HIV/AIDS (much higher risk)</li>
    <li>Malnourished individuals</li>
    <li>People with diabetes</li>
    <li>Smokers</li>
    <li>Children under 5 years</li>
</ul>

<h2>Testing</h2>
<p>TB can be diagnosed with:</p>
<ul>
    <li>Sputum (phlegm) test</li>
    <li>Chest X-ray</li>
    <li>Skin test (tuberculin test)</li>
    <li>Blood tests</li>
</ul>
<p>Testing is usually free at health centers.</p>

<h2>Treatment</h2>
<p class="warning"><b>⚠️ IMPORTANT:</b> TB treatment must be completed fully (usually 6 months). Stopping early leads to drug-resistant TB, which is much harder to treat.</p>
<ul>
    <li>Take all medicines exactly as prescribed</li>
    <li>Complete the full course even if you feel better</li>
    <li>Treatment is usually free in public health facilities</li>
    <li>Directly Observed Therapy (DOT) helps ensure completion</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Vaccination: BCG vaccine protects children from severe TB</li>
    <li>Good ventilation: open windows, spend time outdoors</li>
    <li>Cover mouth when coughing or sneezing</li>
    <li>Early diagnosis and treatment prevents spread</li>
    <li>For people with HIV: ART reduces TB risk</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/tuberculosis">www.who.int/news-room/fact-sheets/detail/tuberculosis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "TB is a leading cause of death globally. Learn about symptoms, testing, treatment (6 months), and prevention including BCG vaccine.",
                source = "WHO",
                category = "Infectious Diseases"
            ),
            
            // DENGUE FEVER - English
            Article(
                title = "Dengue fever",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Dengue is a viral infection spread by Aedes mosquitoes (same mosquitoes that spread Zika and chikungunya).</li>
        <li>Dengue is found in tropical and subtropical climates worldwide, including parts of Africa.</li>
        <li>Most people with dengue have mild or no symptoms; severe dengue can be life-threatening.</li>
        <li>There is no specific treatment for dengue; early detection and proper medical care save lives.</li>
        <li>Prevention focuses on avoiding mosquito bites and eliminating breeding sites.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Dengue fever is caused by a virus transmitted through the bite of infected Aedes mosquitoes. These mosquitoes bite during the day, especially in the early morning and late afternoon. Dengue cannot spread directly from person to person.</p>

<h2>Symptoms</h2>
<h3>Mild dengue (dengue fever):</h3>
<ul>
    <li>High fever (40°C/104°F)</li>
    <li>Severe headache</li>
    <li>Pain behind the eyes</li>
    <li>Muscle and joint pain</li>
    <li>Nausea, vomiting</li>
    <li>Swollen glands</li>
    <li>Rash</li>
</ul>
<p>Symptoms usually last 2–7 days.</p>

<h3 class="warning">⚠️ Severe dengue (dengue hemorrhagic fever) – EMERGENCY:</h3>
<p>Warning signs appear 3–7 days after first symptoms:</p>
<ul>
    <li>Severe abdominal pain</li>
    <li>Persistent vomiting</li>
    <li>Rapid breathing</li>
    <li>Bleeding gums or nose</li>
    <li>Blood in vomit or stool</li>
    <li>Fatigue, restlessness</li>
    <li>Cold, clammy skin</li>
</ul>

<div style="background: linear-gradient(135deg, #FFEBEE 0%, #FFCDD2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #D32F2F;">
    <h3 style="margin-top: 0; color: #B71C1C;">⚠️ Severe dengue – recognise these signs</h3>
    <table style="width: 100%; border-collapse: collapse; font-size: 13px;">
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🤢</span><br/><b>Severe vomiting</b></td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🩸</span><br/><b>Bleeding</b><br/>gums, nose, stool</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😰</span><br/><b>Cold, clammy</b><br/>skin</td>
        </tr>
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😣</span><br/><b>Severe belly</b><br/>pain</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😮‍💨</span><br/><b>Rapid</b><br/>breathing</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😵</span><br/><b>Restlessness</b><br/>or weakness</td>
        </tr>
    </table>
    <p style="margin: 12px 0 0 0; font-weight: bold;">→ Go to hospital immediately</p>
</div>

<p class="warning"><b>⚠️ Seek immediate medical care if you have severe dengue warning signs.</b></p>

<h2>Treatment</h2>
<ul>
    <li>No specific antiviral medicine for dengue</li>
    <li>Rest and drink plenty of fluids</li>
    <li>Use paracetamol (acetaminophen) for pain and fever</li>
    <li><b>Do NOT use aspirin or ibuprofen</b> – can increase bleeding risk</li>
    <li>For severe dengue: hospital care with IV fluids may be needed</li>
</ul>

<h2>Prevention</h2>
<h3>Avoid mosquito bites:</h3>
<ul>
    <li>Use insect repellent (DEET, picaridin)</li>
    <li>Wear long-sleeved shirts and long pants</li>
    <li>Use mosquito nets (especially during day naps)</li>
    <li>Keep windows and doors closed or screened</li>
</ul>

<h3>Eliminate breeding sites:</h3>
<ul>
    <li>Remove standing water (containers, tires, flower pots)</li>
    <li>Cover water storage containers</li>
    <li>Clean gutters and drains</li>
    <li>Change water in vases and pet bowls weekly</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/dengue-and-severe-dengue">www.who.int/news-room/fact-sheets/detail/dengue-and-severe-dengue</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Dengue fever: viral infection from mosquitoes. Symptoms, severe dengue warning signs, treatment, and prevention.",
                source = "WHO",
                category = "Infectious Diseases"
            ),
            
            // YELLOW FEVER - English
            Article(
                title = "Yellow fever",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Yellow fever is a viral disease spread by mosquitoes, found in tropical areas of Africa and South America.</li>
        <li>Yellow fever can cause severe illness and death; about 15% of people with severe disease die.</li>
        <li>A safe and effective vaccine exists; one dose provides lifelong protection.</li>
        <li>Many African countries require yellow fever vaccination certificate for entry.</li>
        <li>There is no specific treatment; prevention through vaccination and mosquito control is essential.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Yellow fever is caused by a virus transmitted by infected mosquitoes. The name "yellow fever" comes from jaundice (yellowing of skin and eyes) that affects some patients. The disease is endemic in many African countries.</p>

<h2>Symptoms</h2>
<p>Many people have no symptoms or mild symptoms. When symptoms occur:</p>

<h3>Initial phase (3–4 days):</h3>
<ul>
    <li>Fever</li>
    <li>Headache</li>
    <li>Muscle pain, especially back pain</li>
    <li>Nausea, vomiting</li>
    <li>Loss of appetite</li>
</ul>

<h3 class="warning">⚠️ Severe phase (toxic phase) – affects about 15%:</h3>
<ul>
    <li>High fever returns</li>
    <li>Jaundice (yellow skin and eyes)</li>
    <li>Dark urine</li>
    <li>Abdominal pain</li>
    <li>Bleeding from mouth, nose, eyes, or stomach</li>
    <li>Vomiting blood</li>
    <li>Kidney failure</li>
</ul>
<p class="warning"><b>⚠️ Severe yellow fever is a medical emergency. Seek immediate care.</b></p>

<h2>Vaccination</h2>
<div class="highlight-box">
    <b>💉 Yellow Fever Vaccine:</b><br/>
    • One dose provides lifelong protection<br/>
    • Safe and effective<br/>
    • Usually required for travel to/from endemic areas<br/>
    • Get vaccinated at least 10 days before travel<br/>
    • Free or low-cost at health centers in endemic countries
</div>

<h2>Who should get vaccinated?</h2>
<ul>
    <li>People living in or traveling to yellow fever risk areas</li>
    <li>Infants 9 months and older (in endemic areas)</li>
</ul>

<h3>Who should NOT get vaccinated (or consult doctor first):</h3>
<ul>
    <li>Infants under 6 months</li>
    <li>People with severe allergy to vaccine components</li>
    <li>People with weakened immune systems</li>
    <li>Pregnant women (unless high risk of exposure)</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>No specific antiviral treatment</li>
    <li>Supportive care: rest, fluids, pain relief</li>
    <li>Hospital care may be needed for severe cases</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li><b>Get vaccinated</b> – most important prevention</li>
    <li>Avoid mosquito bites: use repellent, wear protective clothing</li>
    <li>Use mosquito nets</li>
    <li>Eliminate mosquito breeding sites (standing water)</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/yellow-fever">www.who.int/news-room/fact-sheets/detail/yellow-fever</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Yellow fever: viral disease from mosquitoes. Symptoms, severe phase warning signs, vaccination (lifelong protection), and prevention.",
                source = "WHO",
                category = "Infectious Diseases"
            ),
            
            // SCHISTOSOMIASIS (BILHARZIA) - English
            Article(
                title = "Schistosomiasis (Bilharzia)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Schistosomiasis (bilharzia) is a parasitic disease caused by worms that live in fresh water.</li>
        <li>Over 200 million people worldwide need treatment for schistosomiasis; most live in Africa.</li>
        <li>Infection occurs when skin comes into contact with contaminated fresh water (lakes, rivers, ponds).</li>
        <li>Schistosomiasis is treatable with a single dose of praziquantel medicine.</li>
        <li>Prevention focuses on avoiding contact with contaminated water and improving sanitation.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Schistosomiasis, also called bilharzia, is caused by parasitic worms. People become infected when their skin touches fresh water contaminated with the parasites. The worms penetrate the skin and can cause damage to internal organs over time.</p>

<h2>How infection happens</h2>
<ol>
    <li>Worm eggs are released in urine or stool of infected people</li>
    <li>Eggs hatch in fresh water and infect snails</li>
    <li>Infected snails release larvae into the water</li>
    <li>Larvae penetrate human skin when people swim, bathe, or work in contaminated water</li>
    <li>Worms mature and lay eggs in blood vessels</li>
</ol>

<h2>Symptoms</h2>
<h3>Early symptoms (within days-weeks):</h3>
<ul>
    <li>Itchy rash where larvae entered skin</li>
    <li>Fever, chills</li>
    <li>Cough</li>
    <li>Muscle aches</li>
</ul>

<h3>Chronic infection (months-years later):</h3>
<ul>
    <li>Blood in urine (most common sign)</li>
    <li>Blood in stool</li>
    <li>Abdominal pain</li>
    <li>Diarrhea</li>
    <li>Enlarged liver or spleen</li>
    <li>Fatigue</li>
    <li>In children: poor growth, learning difficulties</li>
</ul>

<h2>Who is at risk?</h2>
<ul>
    <li>People who swim, bathe, or work in fresh water (rivers, lakes, ponds)</li>
    <li>Children playing in contaminated water</li>
    <li>Farmers and fishermen</li>
    <li>People without access to safe water and sanitation</li>
</ul>

<h2>Treatment</h2>
<div class="highlight-box">
    <b>💊 Praziquantel:</b><br/>
    • Single dose or short course treats schistosomiasis<br/>
    • Safe and effective<br/>
    • Usually free through mass drug administration programs<br/>
    • Consult a health worker for proper diagnosis and treatment
</div>

<h2>Prevention</h2>
<ul>
    <li><b>Avoid contact with contaminated fresh water</b> – most important</li>
    <li>Use safe water sources for bathing and washing</li>
    <li>Boil or filter water if you must use surface water</li>
    <li>Wear protective boots if working in water</li>
    <li>Improve sanitation: use latrines, don't urinate/defecate in water</li>
    <li>Participate in mass drug administration programs if available</li>
</ul>

<h2>Safe water practices</h2>
<ul>
    <li>Store water for 24–48 hours before use (larvae die)</li>
    <li>Heat water to 50°C (122°F) kills larvae</li>
    <li>Filter water through fine cloth</li>
    <li>Use treated/piped water when available</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/schistosomiasis">www.who.int/news-room/fact-sheets/detail/schistosomiasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Schistosomiasis (bilharzia): parasitic disease from contaminated fresh water. Symptoms, treatment with praziquantel, and prevention.",
                source = "WHO",
                category = "Infectious Diseases"
            ),
            
            // MALNUTRITION IN CHILDREN - English
            Article(
                title = "Malnutrition in children",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Malnutrition contributes to about 45% of deaths in children under 5 years globally.</li>
        <li>Malnutrition includes both undernutrition (stunting, wasting, underweight) and micronutrient deficiencies.</li>
        <li>Early detection and treatment can prevent long-term health problems and death.</li>
        <li>Exclusive breastfeeding for 6 months and continued breastfeeding with complementary foods is crucial.</li>
        <li>Regular growth monitoring helps identify malnutrition early.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Malnutrition means a child is not getting enough nutrients or the right balance of nutrients. It can cause stunting (low height for age), wasting (low weight for height), underweight, and deficiencies in vitamins and minerals. Malnutrition weakens the immune system and increases risk of infections.</p>

<h2>Types of malnutrition</h2>
<h3>1. Stunting (low height for age):</h3>
<ul>
    <li>Child is too short for their age</li>
    <li>Often caused by long-term poor nutrition</li>
    <li>Can affect brain development</li>
</ul>

<h3>2. Wasting (low weight for height):</h3>
<ul>
    <li>Child is too thin for their height</li>
    <li>Often caused by recent severe food shortage or illness</li>
    <li>High risk of death</li>
</ul>

<h3>3. Underweight (low weight for age):</h3>
<ul>
    <li>Child weighs too little for their age</li>
    <li>Can be due to stunting, wasting, or both</li>
</ul>

<h3>4. Micronutrient deficiencies:</h3>
<ul>
    <li>Lack of vitamins (A, D) or minerals (iron, zinc, iodine)</li>
    <li>Can cause anemia, night blindness, weakened immunity</li>
</ul>

<h2>Signs and symptoms</h2>
<ul>
    <li>Not gaining weight or losing weight</li>
    <li>Thin arms and legs, visible ribs</li>
    <li>Swollen belly, feet, or face</li>
    <li>Dry, flaky skin</li>
    <li>Thin, brittle hair</li>
    <li>Lack of energy, listlessness</li>
    <li>Frequent infections</li>
    <li>Delayed development (not reaching milestones)</li>
</ul>

<h2>👁️ Signs to look for</h2>
<div style="background: linear-gradient(135deg, #FFF3E0 0%, #FFE0B2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #E65100;">
    <table style="width: 100%; border-collapse: collapse; font-size: 13px;">
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">💪</span><br/><b>Thin arms/legs</b><br/>Visible ribs</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🫃</span><br/><b>Swollen belly</b><br/>or feet, face</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🪮</span><br/><b>Dry skin</b><br/>Thin, brittle hair</td>
        </tr>
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😴</span><br/><b>Low energy</b><br/>Listless</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🤒</span><br/><b>Frequent</b><br/>illnesses</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">📉</span><br/><b>Not growing</b><br/>or losing weight</td>
        </tr>
    </table>
</div>

<h2 class="warning">⚠️ Severe acute malnutrition (SAM) – EMERGENCY:</h2>
<p>Signs:</p>
<ul>
    <li>Very thin (severe wasting)</li>
    <li>Swollen feet, hands, or face (edema)</li>
    <li>Very weak, unable to eat</li>
    <li>No appetite</li>
</ul>
<p class="warning"><b>⚠️ Severe malnutrition requires immediate medical care. Children can die without treatment.</b></p>

<h2>Prevention</h2>
<h3>For infants:</h3>
<ul>
    <li><b>Exclusive breastfeeding</b> for first 6 months</li>
    <li>Continue breastfeeding up to 2 years with complementary foods</li>
    <li>Start complementary foods at 6 months (not too early, not too late)</li>
</ul>

<h3>For children:</h3>
<ul>
    <li>Feed a variety of foods: grains, legumes, vegetables, fruits, protein</li>
    <li>Feed frequently: 3 meals + 2 snacks per day</li>
    <li>Ensure adequate portions</li>
    <li>Include iron-rich foods (meat, beans, dark leafy greens)</li>
    <li>Vitamin A-rich foods (orange vegetables, fruits)</li>
    <li>Good hygiene: wash hands, clean food preparation</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li><b>Mild to moderate:</b> Nutritional counseling, therapeutic foods, micronutrient supplements</li>
    <li><b>Severe:</b> Hospital care with therapeutic milk/formula, treatment of infections, gradual refeeding</li>
    <li>Treat underlying causes (diarrhea, infections, parasites)</li>
    <li>Regular follow-up and growth monitoring</li>
</ul>

<h2>Growth monitoring</h2>
<div class="highlight-box">
    <b>📊 Regular growth checks:</b><br/>
    • Weigh and measure children regularly<br/>
    • Use growth charts to track progress<br/>
    • Compare to WHO growth standards<br/>
    • Early detection allows early intervention
</div>

<h2>When to seek help</h2>
<ul>
    <li>Child not gaining weight or losing weight</li>
    <li>Signs of severe malnutrition (swelling, extreme thinness)</li>
    <li>Child refuses to eat or drink</li>
    <li>Frequent illnesses</li>
    <li>Delayed development milestones</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/malnutrition">www.who.int/news-room/fact-sheets/detail/malnutrition</a>
    </div>
    <div class="source-item">
        <div class="source-name">UNICEF</div>
        <a href="https://www.unicef.org/nutrition">www.unicef.org/nutrition</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Malnutrition in children: types (stunting, wasting), signs, prevention through breastfeeding and diverse foods, and treatment.",
                source = "WHO, UNICEF",
                category = "Child Health"
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
            
            // WATER - English (combined treatment + storage)
            Article(
                title = "Drinking Water: Treatment and Safe Storage",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Over 2 billion people lack access to safely managed drinking water.</li>
        <li>Contaminated water transmits cholera, typhoid, hepatitis A and E, and diarrheal disease.</li>
        <li>Household water treatment and safe storage (HWTS) reduce diarrheal disease and save lives.</li>
        <li>Safe water could prevent an estimated 400,000 deaths annually.</li>
    </ul>
</div>

<h2>Water treatment at home</h2>
<h3>1. Boiling (most effective):</h3>
<ul>
    <li>Bring water to a <b>rolling boil</b> for at least 1 minute (3 minutes at elevations above 6,500 ft / 2,000 m).</li>
    <li>Kills viruses, bacteria, and parasites.</li>
    <li>Let cool naturally; store in a clean, covered container.</li>
</ul>

<h3>2. Chlorination:</h3>
<ul>
    <li>Use purification tablets or liquid chlorine (e.g. 2 drops of household bleach per litre of clear water).</li>
    <li>Follow product instructions. Wait 30 minutes before drinking.</li>
    <li>Chlorine kills most bacteria and viruses; less effective against some parasites (e.g. Cryptosporidium).</li>
</ul>

<h3>3. Other options (when available):</h3>
<ul>
    <li><b>Solar disinfection (SODIS):</b> Clear plastic bottles in strong sunlight for 6–8 hours (or 2 days if cloudy).</li>
    <li><b>Filters:</b> Certified filters can remove bacteria and some parasites; follow manufacturer instructions.</li>
</ul>

<hr/>

<h2>Safe water storage</h2>
<p>Keeping treated water in containers that protect it from recontamination is essential.</p>
<ul>
    <li>Use a <b>clean, covered container</b> with a tap or narrow opening so hands and dirty cups do not enter.</li>
    <li>Keep the container <b>off the ground</b> to avoid contact with dirt and pests.</li>
    <li>Do not put hands or dirty utensils inside; pour water out or use a clean ladle.</li>
    <li>Wash and dry the container regularly. Use stored water within 1–2 days if not treated with chlorine.</li>
</ul>

<hr/>

<h2>Hand hygiene</h2>
<p>Wash hands with soap and clean water:</p>
<ul>
    <li>Before eating or preparing food</li>
    <li>After using the toilet</li>
    <li>After changing diapers or cleaning a child</li>
    <li>After touching animals</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/drinking-water">www.who.int/news-room/fact-sheets/detail/drinking-water</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO – Household water treatment and safe storage</div>
        <a href="https://www.who.int/teams/environment-climate-change-and-health/water-sanitation-and-health/water-safety-and-quality/household-water-treatment-and-safe-storage">who.int/.../household-water-treatment-and-safe-storage</a>
    </div>
    <div class="source-item">
        <div class="source-name">CDC – Household Water Treatment &amp; Safe Water Storage</div>
        <a href="https://www.cdc.gov/global-water-sanitation-hygiene/about/about-household-water-treatment.html">www.cdc.gov/global-water-sanitation-hygiene/about/about-household-water-treatment</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Safe drinking water: treatment (boiling, chlorine, filters) and safe storage at home. WHO, CDC.",
                source = "WHO, UNICEF, CDC",
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

<h2>👁️ Quick visual guide</h2>
<div style="background: #f5f5f5; border-radius: 12px; padding: 16px; margin: 16px 0;">
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px; margin: 4px;">
                <div style="text-align: center;"><span style="font-size: 36px;">🩹</span></div>
                <div style="text-align: center; font-weight: bold;">Bleeding</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Press firmly with clean cloth. Raise limb above heart.</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px;">
                <div style="text-align: center;"><span style="font-size: 36px;">💧</span></div>
                <div style="text-align: center; font-weight: bold;">Burns</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Cool with clean water 10–20 min. No butter or oil.</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px;">
                <div style="text-align: center;"><span style="font-size: 36px;">🫳</span></div>
                <div style="text-align: center; font-weight: bold;">Choking</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Behind person. Upward thrusts above belly button.</p>
            </td>
        </tr>
    </table>
</div>

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
            
            // MENSTRUAL HEALTH - English
            Article(
                title = "Menstrual health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Menstruation is a normal part of reproductive health; cycles typically last 21–35 days.</li>
        <li>Heavy bleeding, severe pain, or irregular cycles can signal a health problem.</li>
        <li>Good hygiene (clean materials, washing) reduces infection risk.</li>
        <li>Pain can often be eased with rest, heat, and safe pain relievers (e.g. ibuprofen).</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Menstrual health includes having regular, manageable periods and access to information, products, and care. Many women and girls experience pain (cramps), mood changes, or fatigue around their period; these are often normal but can sometimes need medical attention.</p>

<h2>When to see a health worker</h2>
<ul>
    <li>Very heavy bleeding (soaking a pad or tampon every 1–2 hours)</li>
    <li>Severe pain that does not improve with rest or pain relief</li>
    <li>Periods that last longer than 7 days or come more often than every 21 days</li>
    <li>No period for more than 3 months (and you are not pregnant)</li>
    <li>Fever or bad-smelling discharge during your period</li>
</ul>

<h2>Self-care</h2>
<ul>
    <li>Use clean absorbent materials (pads, cloth, or cups) and change them regularly</li>
    <li>Wash hands before and after changing materials</li>
    <li>Rest and apply warmth (e.g. hot water bottle) for cramps</li>
    <li>Eat regularly and stay hydrated</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/sexual-and-reproductive-health">www.who.int – Sexual and reproductive health</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Menstrual health: normal cycles, when to seek care, hygiene, and self-care for cramps and heavy bleeding.",
                source = "WHO",
                category = "General Health"
            ),
            
            // MIGRAINES - English
            Article(
                title = "Migraines",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Migraine is a common headache disorder, often one-sided and throbbing, lasting hours to days.</li>
        <li>Many people also have nausea, sensitivity to light or sound, or visual changes (aura).</li>
        <li>Triggers can include stress, lack of sleep, certain foods, bright lights, or hormonal changes.</li>
        <li>Rest in a dark, quiet room and pain relievers can help; severe or new headaches need a doctor.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Migraine is more than a simple headache. It often causes moderate to severe pain on one side of the head, sometimes with nausea, vomiting, and sensitivity to light or sound. Some people see flashing lights or zigzag lines (aura) before the pain starts.</p>

<h2>Common triggers</h2>
<ul>
    <li>Stress or anxiety</li>
    <li>Too little or irregular sleep</li>
    <li>Skipping meals or dehydration</li>
    <li>Bright lights, loud noise, or strong smells</li>
    <li>Certain foods (e.g. aged cheese, chocolate, alcohol)</li>
    <li>Hormonal changes (e.g. around periods)</li>
</ul>

<h2>What helps</h2>
<ul>
    <li>Rest in a dark, quiet room</li>
    <li>Cold or warm compress on forehead or neck</li>
    <li>Over-the-counter pain relievers (e.g. ibuprofen, paracetamol) as directed</li>
    <li>Drinking water and eating something light if you can</li>
</ul>

<p class="warning"><b>⚠️ See a health worker if:</b> headache is sudden and very severe (“worst ever”), you have fever or stiff neck, confusion, or weakness on one side of the body.</p>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/headache-disorders">www.who.int/news-room/fact-sheets/detail/headache-disorders</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Migraines: key facts, triggers, self-care, and when to seek medical help for headaches.",
                source = "WHO",
                category = "General Health"
            ),
            
            // ALLERGIES - English
            Article(
                title = "Allergies",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Allergies happen when the immune system reacts to something harmless (e.g. pollen, food, insect stings).</li>
        <li>Mild symptoms include sneezing, runny nose, itchy eyes or skin, and rash.</li>
        <li>Severe allergic reactions (anaphylaxis) can cause swelling, difficulty breathing, and collapse—this is an emergency.</li>
        <li>Avoiding the trigger and having an action plan (e.g. antihistamines, epinephrine) can prevent or treat reactions.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Allergic reactions range from mild (sneezing, itching, rash) to life-threatening (swelling of throat, trouble breathing). Common triggers include pollen, dust, certain foods (e.g. nuts, shellfish), insect stings, and some medicines.</p>

<h2>Mild to moderate symptoms</h2>
<ul>
    <li>Sneezing, runny or blocked nose</li>
    <li>Itchy, watery eyes</li>
    <li>Itchy skin or hives (raised, red patches)</li>
    <li>Mild stomach upset (e.g. after a food)</li>
</ul>
<p>Antihistamines (as advised by a pharmacist or doctor) and avoiding the trigger often help.</p>

<h2>Severe reaction (anaphylaxis) – emergency</h2>
<p>Signs: swelling of face, lips, or throat; difficulty breathing or swallowing; wheezing; sudden weakness or collapse; fast heartbeat.</p>

<h2>👁️ Mild vs severe at a glance</h2>
<div style="background: #f5f5f5; border-radius: 12px; padding: 16px; margin: 16px 0;">
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 12px; vertical-align: top; width: 50%; background: #E8F5E9; border-radius: 8px;">
                <div style="text-align: center; font-weight: bold;">😊 Mild / moderate</div>
                <p style="font-size: 13px; margin: 8px 0 0 0;">🤧 Sneezing, runny nose<br/>👀 Itchy eyes<br/>🔴 Rash, hives<br/>Antihistamines often help</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 50%; background: #FFEBEE; border-radius: 8px;">
                <div style="text-align: center; font-weight: bold;">🚨 Severe (anaphylaxis)</div>
                <p style="font-size: 13px; margin: 8px 0 0 0;">😮 Swelling face/lips/throat<br/>😮‍💨 Difficulty breathing<br/>😵 Collapse, weak pulse<br/><b>→ Use epinephrine, call emergency</b></p>
            </td>
        </tr>
    </table>
</div>

<p class="warning"><b>⚠️ This is a medical emergency.</b> Use an epinephrine auto-injector if prescribed, then get emergency care immediately.</p>

<h2>Prevention</h2>
<ul>
    <li>Identify and avoid known triggers (foods, environments, insects)</li>
    <li>Carry prescribed allergy medicine or epinephrine if you have a history of severe reactions</li>
    <li>Tell family and close contacts what to do in case of a severe reaction</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/questions-and-answers/item/allergies">www.who.int – Allergies Q&A</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Allergies: mild vs severe symptoms, anaphylaxis emergency signs, and prevention.",
                source = "WHO",
                category = "General Health"
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
            ),
            
            // RAPID TESTS - English
            Article(
                title = "How to Use Rapid Tests (COVID-19 & Malaria)",
                content = """
<div class="highlight-box">
    <b>📋 Before you start</b><br/>
    • Read the test kit instructions – brands can vary.<br/>
    • Use before the expiry date.<br/>
    • Store in a cool, dry place. Keep in the foil until use.
</div>

<div class="key-facts">
    <h2>Quick guide</h2>
    <ul>
        <li>Rapid tests give results in about 15–30 minutes at home or in the clinic.</li>
        <li>Malaria RDTs detect malaria parasite in a drop of blood.</li>
        <li>COVID-19 rapid tests detect the virus from a nasal or throat swab.</li>
        <li>A negative result does not always rule out infection – follow health advice.</li>
    </ul>
</div>

<hr/>

<h2>🩸 Malaria rapid test (RDT)</h2>

<h3>What you need</h3>
<ul>
    <li>Test device (in sealed pouch)</li>
    <li>Lancet (small needle) or finger-prick device</li>
    <li>Buffer solution (small bottle or dropper)</li>
    <li>Alcohol wipe and clean tissue</li>
</ul>

<h3>Steps</h3>
<ol>
    <li><b>Wash hands</b> with soap and dry well.</li>
    <li><b>Open the pouch</b> only when ready. Take out the test device and place it on a clean, flat surface.</li>
    <li><b>Clean the finger</b> (usually ring or middle finger) with the alcohol wipe. Let it dry.</li>
    <li><b>Prick the finger</b> with the lancet. Gently squeeze to get a small drop of blood.</li>
    <li><b>Add the blood</b> to the round well (sample area) on the test device – usually 1 drop or as the leaflet says.</li>
    <li><b>Add the buffer</b> – squeeze the correct number of drops into the same well or the buffer well. Check the kit instructions (often 2–4 drops).</li>
    <li><b>Wait 15–20 minutes</b>. Do not touch the device. Set a timer.</li>
    <li><b>Read the result</b> (see below). Do not read after 30 minutes.</li>
</ol>

<h3>How to read the result</h3>
<div style="background: #E8F5E9; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #4CAF50;">
    <p style="margin: 0 0 8px 0;"><b>✅ NEGATIVE (no malaria):</b></p>
    <p style="margin: 0;">Only <b>one line</b> appears – in the <b>Control (C)</b> zone. The result is valid.</p>
</div>
<div style="background: #FFEBEE; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #D32F2F;">
    <p style="margin: 0 0 8px 0;"><b>⚠️ POSITIVE (malaria likely):</b></p>
    <p style="margin: 0;"><b>Two lines</b> appear – one at <b>Control (C)</b> and one at <b>Test (T)</b>. Seek care and treatment as advised.</p>
</div>
<div style="background: #FFF8E1; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #F9A825;">
    <p style="margin: 0 0 8px 0;"><b>❌ Invalid:</b></p>
    <p style="margin: 0;">No line at <b>Control (C)</b>. The test did not work. Use a new test kit.</p>
</div>

<p><b>Summary:</b> C only = negative. C + T = positive. No C = invalid.</p>

<hr/>

<h2>🦠 COVID-19 rapid antigen test</h2>

<h3>What you need</h3>
<ul>
    <li>Test device (in sealed pouch)</li>
    <li>Swab (long cotton tip)</li>
    <li>Buffer solution (small tube with cap)</li>
    <li>Timer</li>
</ul>

<h3>Steps</h3>
<ol>
    <li><b>Wash hands</b> with soap and dry well. Blow your nose gently, then wash hands again.</li>
    <li><b>Open the kit</b> and place the test device on a clean, flat surface. Open the buffer tube – do not spill.</li>
    <li><b>Swab</b> – Insert the soft end of the swab about 2 cm into one nostril, press gently against the inside for 10–15 seconds. Use the same swab in the other nostril the same way. (Some kits use throat + nose – follow the leaflet.)</li>
    <li><b>Put the swab in the buffer</b> – Place the swab into the buffer tube and swirl or press against the sides for the time given (often 10–30 seconds). Squeeze the tube and remove the swab as instructed.</li>
    <li><b>Add drops to the test</b> – Close the buffer cap (if it has a nozzle) or use the dropper. Add the number of drops shown on the leaflet onto the sample well (S) of the test device.</li>
    <li><b>Wait</b> – Usually 15–30 minutes. Do not move the device. Set a timer.</li>
    <li><b>Read the result</b> (see below). Do not read after the maximum time stated (e.g. 30 min).</li>
</ol>

<h3>How to read the result</h3>
<div style="background: #E8F5E9; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #4CAF50;">
    <p style="margin: 0 0 8px 0;"><b>✅ NEGATIVE (no COVID-19 detected):</b></p>
    <p style="margin: 0;">Only <b>one line</b> at <b>Control (C)</b>. The test worked and the result is negative at the time of testing.</p>
</div>
<div style="background: #FFEBEE; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #D32F2F;">
    <p style="margin: 0 0 8px 0;"><b>⚠️ POSITIVE (COVID-19 likely):</b></p>
    <p style="margin: 0;"><b>Two lines</b> – one at <b>Control (C)</b> and one at <b>Test (T)</b>. Even a faint line at T is positive. Isolate and follow local health advice.</p>
</div>
<div style="background: #FFF8E1; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #F9A825;">
    <p style="margin: 0 0 8px 0;"><b>❌ Invalid:</b></p>
    <p style="margin: 0;">No line at <b>Control (C)</b>. The test failed. Use a new test kit.</p>
</div>

<p><b>Summary:</b> C only = negative. C + T = positive. No C = invalid.</p>

<hr/>

<h2>⚠️ Important</h2>
<ul>
    <li>Rapid tests are a guide, not a replacement for a health worker’s assessment.</li>
    <li>If you have strong symptoms (e.g. high fever, difficulty breathing) but the test is negative, still seek care.</li>
    <li>Dispose of lancets and swabs safely (e.g. in a closed container, away from children).</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">WHO – Malaria rapid diagnostic tests</div>
        <a href="https://www.who.int/news-room/questions-and-answers/item/malaria-rapid-diagnostic-tests">WHO Malaria RDTs</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO – COVID-19 antigen-detection tests</div>
        <a href="https://www.who.int/publications/i/item/WHO-2019-nCoV-Antigen_Detection-2021.1">WHO COVID-19 Antigen Tests</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Simple step-by-step guide to using rapid tests for malaria and COVID-19. Easy-to-interpret results: one line vs two lines, positive and negative.",
                source = "WHO",
                category = "Infectious Diseases"
            ),

            // === 30 articles for Uganda & Togo (English) ===
            // SCHISTOSOMIASIS (BILHARZIA) - English (expanded)
            Article(
                title = "Schistosomiasis (Bilharzia)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Schistosomiasis (bilharzia) is a parasitic disease caused by blood flukes (schistosomes). Infection happens when skin contacts freshwater where infected snails release parasite larvae.</li>
        <li>About 240 million people worldwide need treatment; over 90% of them live in the WHO African Region. It is common in Uganda and Togo where people use freshwater lakes and rivers.</li>
        <li>Intestinal form: abdominal pain, diarrhoea, blood in stool. Urogenital form: blood in urine, bladder and kidney damage. In children it can cause anaemia, stunting, and reduced learning.</li>
        <li>Praziquantel is the recommended, effective, low-cost treatment. There is no vaccine; prevention relies on avoiding contact with contaminated freshwater and using safe water.</li>
    </ul>
</div>

<h2>How you get it</h2>
<ul>
    <li>Skin contact with freshwater where infected snails live (swimming, bathing, washing clothes).</li>
    <li>Drinking or using contaminated water.</li>
</ul>

<h2>Symptoms</h2>
<ul>
    <li><b>Early:</b> Itchy rash where parasite entered; later fever, chills, cough, stomach pain.</li>
    <li><b>Intestinal:</b> Abdominal pain, diarrhoea, blood in stool.</li>
    <li><b>Urogenital:</b> Blood in urine, pain when passing urine; in children, poor growth, anaemia. Seek care if you have these signs.</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Avoid swimming or wading in freshwater in high-risk areas.</li>
    <li>Use safe water for drinking and washing; boil or treat water when needed.</li>
    <li>Use proper sanitation to reduce contamination of water bodies.</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>Praziquantel from a health centre is effective for all major forms.</li>
    <li>Take as prescribed; early treatment prevents long-term damage.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/schistosomiasis">www.who.int/news-room/fact-sheets/detail/schistosomiasis</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NIAID</div>
        <a href="https://www.niaid.nih.gov/diseases-conditions/schistosomiasis-bilharzia">www.niaid.nih.gov – Schistosomiasis (Bilharzia)</a>
    </div>
    <div class="source-item">
        <div class="source-name">NIH – NCBI Bookshelf</div>
        <a href="https://www.ncbi.nlm.nih.gov/books/NBK554434/">www.ncbi.nlm.nih.gov/books – Schistosomiasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Schistosomiasis (bilharzia): spread by freshwater snails, symptoms, prevention, and treatment with praziquantel. WHO, NIH sources.",
                source = "WHO, NIH",
                category = "Infectious Diseases"
            ),
            // RIVER BLINDNESS (ONCHOCERCIASIS) - English
            Article(
                title = "River Blindness (Onchocerciasis)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>River blindness is caused by the parasitic worm <i>Onchocerca volvulus</i>, spread by the bite of infected blackflies that breed near fast-flowing rivers and streams.</li>
        <li>More than 99% of infected people live in sub-Saharan Africa and Yemen; the disease also exists in parts of Latin America.</li>
        <li>Symptoms include severe itching, disfiguring skin conditions, and visual impairment including permanent blindness.</li>
        <li>Population-based treatment with ivermectin (mass drug administration, MDA) is the core strategy; ivermectin is taken at least once yearly for 10–15 years in endemic areas.</li>
        <li>In 2024, over 171 million people were treated for onchocerciasis worldwide; several countries have been verified free of the disease.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Onchocerciasis is transmitted when an infected blackfly (<i>Simulium</i>) bites a person. The fly picks up immature worms (microfilariae) from an infected person and, after development, passes infective larvae to another person. Inside the human host, larvae mature into adult worms that form nodules under the skin and produce microfilariae. The microfilariae move through the skin and eyes; when they die they trigger intense inflammation, leading to itching, skin changes, and eye damage that can result in blindness.</p>

<h2>Symptoms</h2>
<ul>
    <li><b>Skin:</b> Severe itching, rash, thickening or discolouration of skin, nodules under the skin.</li>
    <li><b>Eyes:</b> Eye irritation, sensitivity to light, lesions of the cornea and other parts of the eye, visual impairment, and permanent blindness (often after many years of infection).</li>
    <li>Early exposure to infection is associated with an increased risk of epilepsy in children in some areas.</li>
</ul>

<h2>Prevention and treatment</h2>
<p><b>Treatment:</b> Take ivermectin when offered during mass drug administration (MDA) campaigns. WHO recommends treatment at least once yearly for 10–15 years in endemic areas. Do not skip doses—communities need sustained treatment to eliminate transmission.</p>
<p><b>Prevention:</b> There is no vaccine. Reduce exposure to blackfly bites by avoiding areas near fast-flowing rivers at peak biting times (often daytime), wearing long sleeves and trousers, and using insect repellent where available.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/onchocerciasis">www.who.int/news-room/fact-sheets/detail/onchocerciasis</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/filarial-worms/about/onchocerciasis.html">www.cdc.gov/filarial-worms/about/onchocerciasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "River blindness: spread by blackflies, symptoms, and the importance of ivermectin mass drug administration. WHO and CDC sources.",
                source = "WHO, CDC",
                category = "Infectious Diseases"
            ),

            // LYMPHATIC FILARIASIS (ELEPHANTIASIS) - English (expanded)
            Article(
                title = "Lymphatic Filariasis (Elephantiasis)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Lymphatic filariasis (elephantiasis) is caused by parasitic roundworms transmitted by mosquito bites. The worms damage the lymphatic system and can cause severe swelling of legs, arms, or genitals.</li>
        <li>Over 856 million people in 52 countries are at risk. About 36 million have chronic disease, including lymphoedema and hydrocele (scrotal swelling in men).</li>
        <li>Most infected people have no symptoms at first; swelling and disability often develop over months or years. Early treatment prevents long-term disability.</li>
        <li>Preventive chemotherapy (mass drug administration, MDA) with safe medicines given yearly for at least 5 years can eliminate the disease in affected communities.</li>
    </ul>
</div>

<h2>How it spreads</h2>
<ul>
    <li>Infected mosquitoes (Culex, Anopheles, Aedes) bite people; repeated bites over time usually needed.</li>
    <li>Uganda and Togo are among affected countries; MDA programmes run in many endemic areas.</li>
</ul>

<h2>Symptoms</h2>
<ul>
    <li><b>Early:</b> Fever, swollen lymph nodes, pain.</li>
    <li><b>Later:</b> Swelling of legs, arms, breasts, or genitals (elephantiasis); skin thickening; in men, hydrocele (scrotal swelling).</li>
    <li>Bacterial skin infections can worsen swelling. Early treatment prevents disability.</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>Take MDA medicines when offered—once a year for at least 5 years.</li>
    <li>Do not skip; community-wide treatment is needed to stop transmission.</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Use insecticide-treated bed nets.</li>
    <li>Avoid mosquito bites (repellent, long sleeves, stay indoors at peak biting times).</li>
    <li>No vaccine available.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis">www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/filarial-worms/about/lymphatic-filariasis.html">www.cdc.gov/filarial-worms/about/lymphatic-filariasis</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NCBI Bookshelf</div>
        <a href="https://www.ncbi.nlm.nih.gov/books/NBK556012/">www.ncbi.nlm.nih.gov/books – Filariasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Lymphatic filariasis (elephantiasis): spread by mosquitoes, symptoms, MDA treatment, and prevention. WHO, CDC, NIH sources.",
                source = "WHO, CDC, NIH",
                category = "Infectious Diseases"
            ),

            // TRACHOMA - English
            Article(
                title = "Trachoma",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Trachoma is a bacterial eye infection caused by <i>Chlamydia trachomatis</i> and is the leading infectious cause of preventable blindness worldwide.</li>
        <li>Infection spreads through contact with eye or nose discharge (hands, clothing, bedding, shared items) and by flies that have been in contact with infected discharge.</li>
        <li>Repeated infections over years cause scarring of the inner eyelid; the eyelashes then turn inward (trichiasis) and scratch the cornea, leading to pain and blindness.</li>
        <li>About 1.9 million people are blind or visually impaired from trachoma; 103 million people live in trachoma-endemic areas (2025). Most cases are in poor, rural areas of Africa, Asia, and the Middle East.</li>
        <li>WHO’s SAFE strategy (Surgery for trichiasis, Antibiotics, Facial cleanliness, Environmental improvement) is used to eliminate trachoma. In 2024, 87 349 people had trichiasis surgery and 44.4 million received antibiotics.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Trachoma is contagious and often affects young children, who are the main reservoir of infection. Symptoms can start with mild itching and irritation of the eyes and eyelids, then swollen eyelids and pus or mucus from the eyes. The disease progresses slowly; the most painful and blinding stage (trichiasis—in-turned eyelashes) often appears in adulthood. Women are affected more often than men, partly due to closer contact with infected children.</p>

<h2>Symptoms</h2>
<ul>
    <li>Itching and irritation of the eyes and eyelids</li>
    <li>Eye redness, pain, sensitivity to light</li>
    <li>Swollen eyelids, discharge containing mucus or pus</li>
    <li>Scarring of the inner eyelid (visible as white lines with magnification)</li>
    <li>Eyelashes turning inward (trichiasis), rubbing the eye and damaging the cornea</li>
    <li>Clouding of the cornea and vision loss, which may become permanent</li>
</ul>

<h2>Prevention and treatment</h2>
<p><b>SAFE strategy:</b></p>
<ul>
    <li><b>S</b>urgery for trachomatous trichiasis (in-turned eyelashes) to prevent blindness.</li>
    <li><b>A</b>ntibiotics (e.g. azithromycin) to clear infection—take when offered in mass treatment campaigns.</li>
    <li><b>F</b>acial cleanliness: wash children’s faces and your own hands to reduce spread.</li>
    <li><b>E</b>nvironmental improvement: access to clean water, sanitation, and fly control (e.g. proper waste disposal) to reduce transmission.</li>
</ul>
<p>Seek care if you or your child has itchy or irritated eyes or discharge, especially if you live in or have travelled to an area where trachoma is common. Early treatment helps prevent serious damage.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/trachoma">www.who.int/news-room/fact-sheets/detail/trachoma</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/trachoma/symptoms-causes/syc-20378505">www.mayoclinic.org/diseases-conditions/trachoma</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Eye Institute (NIH)</div>
        <a href="https://www.nei.nih.gov/about/our-impact/nei-research-initiatives/international-vision-research/improving-global-vision-path-eliminating-trachoma">www.nei.nih.gov – Trachoma elimination</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Trachoma: leading infectious cause of preventable blindness. Symptoms, SAFE strategy, and sources from WHO, Mayo Clinic, and NIH.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),

            // SOIL-TRANSMITTED HELMINTHS (WORMS) - English
            Article(
                title = "Soil-Transmitted Helminths (Worms)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Soil-transmitted helminths (STH) are parasitic worms that infect the intestine. An estimated 1.5 billion people (about 24% of the world’s population) are infected, mainly in tropical and subtropical areas with poor sanitation.</li>
        <li>The main types are roundworm (<i>Ascaris</i>), whipworm (<i>Trichuris</i>), and hookworms (<i>Necator</i>, <i>Ancylostoma</i>). They are spread by eggs in human faeces that contaminate soil; hookworm larvae can also enter through the skin when walking barefoot.</li>
        <li>Infected children are often nutritionally and physically impaired; in girls and women of reproductive age, blood loss from hookworm can worsen anaemia and increase the risk of maternal and infant mortality.</li>
        <li>Safe, effective medicines (albendazole 400 mg or mebendazole 500 mg) are used for deworming. WHO recommends periodic deworming without prior individual diagnosis for at-risk people in endemic areas.</li>
        <li>Control also depends on health education, handwashing, using latrines, wearing shoes, and improved access to clean water and sanitation.</li>
    </ul>
</div>

<h2>Overview</h2>
<p>Soil-transmitted helminths are transmitted by eggs passed in the faeces of infected people. In areas with inadequate sanitation, these eggs contaminate the soil. People become infected by ingesting eggs (e.g. from contaminated hands, water, or unwashed/uncooked vegetables) or, in the case of hookworm, when larvae in soil penetrate the skin—often when walking barefoot. There is no direct person-to-person spread; eggs need about 3 weeks in soil to become infective. Pinworm (another common intestinal worm, especially in children) spreads when eggs are swallowed or inhaled from contaminated surfaces or hands.</p>

<h2>Types and transmission</h2>
<ul>
    <li><b>Roundworm and whipworm:</b> Eggs are swallowed from contaminated soil, water, or food.</li>
    <li><b>Hookworm:</b> Eggs hatch in soil; larvae penetrate the skin (e.g. bare feet), then travel to the intestine.</li>
    <li>Infection is common in preschool and school-age children, and in women of reproductive age in endemic areas.</li>
</ul>

<h2>Symptoms</h2>
<p>Light infections may cause no symptoms. Heavier infections can cause:</p>
<ul>
    <li>Abdominal pain, diarrhoea (whipworm can cause dysentery)</li>
    <li>Anaemia (especially with hookworm due to blood loss)</li>
    <li>Malnutrition, poor growth, and tiredness</li>
    <li>Loss of appetite and reduced physical fitness</li>
    <li>In pinworm: anal or vaginal itching (especially at night), restless sleep; many people have no symptoms</li>
</ul>
<p>Very heavy worm loads can rarely cause intestinal obstruction, which needs urgent care.</p>

<h2>Prevention and treatment</h2>
<p><b>Treatment:</b> Take deworming medicine (e.g. albendazole or mebendazole) when offered at school, during child health days, or at a clinic. Entire households may need treatment for pinworm. Complete the dose as advised.</p>
<p><b>Prevention:</b> Wash hands with soap after using the toilet and before eating; use latrines and keep them clean; wear shoes to reduce hookworm; wash and cook vegetables properly; drink safe water. Keep fingernails short and avoid scratching the anal area to reduce pinworm spread. Wash bedding and underwear in hot water if someone at home has pinworm.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections">www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/sth/about/index.html">www.cdc.gov/sth/about</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic (Pinworm)</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/pinworm/symptoms-causes/syc-20376382">www.mayoclinic.org/diseases-conditions/pinworm</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NCBI</div>
        <a href="https://www.ncbi.nlm.nih.gov/books/NBK560525/">www.ncbi.nlm.nih.gov/books – Helminthiasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Intestinal worms (roundworm, whipworm, hookworm): symptoms, deworming with albendazole/mebendazole, and prevention. WHO, CDC, Mayo Clinic, NIH sources.",
                source = "WHO, CDC, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            // HANDWASHING - English (expanded)
            Article(
                title = "Handwashing: When and How",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Hand hygiene is one of the most cost-effective ways to prevent diarrhoea, respiratory infections, and many other illnesses. WHO and UNICEF promote hand hygiene for all.</li>
        <li>Handwashing with soap can reduce diarrhoeal disease by about 30% and acute respiratory infections by up to 20%.</li>
        <li>Wash for at least 20 seconds with soap and running water. If soap is not available, use ash or clean water—soap is best.</li>
    </ul>
</div>

<h2>When to wash hands</h2>
<ul>
    <li>Before eating or preparing food.</li>
    <li>After using the toilet; after cleaning a child or changing nappies.</li>
    <li>After touching animals or animal waste; after blowing your nose, coughing, or sneezing.</li>
    <li>Before and after caring for someone who is sick; when hands look dirty.</li>
</ul>

<h2>How to wash</h2>
<ul>
    <li>Use soap and clean, running water.</li>
    <li>Rub for at least 20 seconds—backs of hands, between fingers, under nails. (Tip: hum “Happy Birthday” twice.)</li>
    <li>Rinse well and dry with a clean cloth or air dry.</li>
</ul>

<h2>If no soap available</h2>
<ul>
    <li>Use ash and water, or clean water alone—soap is best.</li>
    <li>When soap and water aren’t available: use hand rub with at least 60% alcohol.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/publications/m/item/hand-hygiene-why-how-when">www.who.int – Hand Hygiene: Why, How & When?</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/hand-washing/art-20046253">www.mayoclinic.org – Hand-washing</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/patientinstructions/000972.htm">medlineplus.gov – Handwashing</a>
    </div>
</div>
                """.trimIndent(),
                summary = "When and how to wash hands to prevent illness. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Water & Sanitation"
            ),
            // POSTPARTUM HAEMORRHAGE - English (expanded)
            Article(
                title = "Postpartum Haemorrhage: Danger Signs",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Postpartum haemorrhage (PPH) is heavy bleeding after birth—often defined as 500 ml or more within 24 hours after vaginal birth. It is one of the main direct causes of maternal death worldwide.</li>
        <li>Most PPH happens right after delivery. Common causes: uterus not contracting well (uterine atony), retained placenta, tears, or clotting problems.</li>
        <li>Danger signs: soaking more than one pad per hour; passing large clots (e.g. bigger than a golf ball); dizziness; fast heartbeat; pale or clammy skin; feeling faint.</li>
        <li>Seek emergency care immediately. In a health facility, oxytocin and other treatments save lives.</li>
    </ul>
</div>

<h2>What is PPH?</h2>
<ul>
    <li>Heavy bleeding after the baby is born; one of the main direct causes of maternal death worldwide.</li>
    <li>Primary PPH: within 24 hours. Late PPH: up to 12 weeks after birth.</li>
</ul>

<h2>Danger signs</h2>
<ul>
    <li>Soaking more than one pad per hour; large blood clots (e.g. bigger than a golf ball).</li>
    <li>Dizziness or fainting; fast heartbeat; pale, cold, or clammy skin.</li>
    <li>Pain or swelling in the vaginal area; extreme tiredness. Any of these need urgent care.</li>
</ul>

<h2>What to do</h2>
<ul>
    <li>Seek emergency care immediately.</li>
    <li>Lie down; keep warm; get to a hospital or health centre as fast as possible.</li>
    <li>In facility: oxytocin and other treatments save lives; early treatment leads to full recovery for most women.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/tools/bleeding-after-birth">www.who.int – Bleeding after birth</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/healthy-lifestyle/labor-and-delivery/in-depth/postpartum-complications/art-20446702">www.mayoclinic.org – Postpartum complications</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NCBI Bookshelf</div>
        <a href="https://www.ncbi.nlm.nih.gov/books/NBK499988/">www.ncbi.nlm.nih.gov/books – Postpartum Hemorrhage</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Recognise heavy bleeding after birth and get help fast. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Maternal Health"
            ),
            // NEWBORN CARE: FIRST 24 HOURS - English (expanded)
            Article(
                title = "Newborn Care: First 24 Hours",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>WHO recommends immediate skin-to-skin contact after birth: place the baby (naked or in a nappy) on the mother’s bare chest for at least 1 hour, until after the first breastfeed.</li>
        <li>Skin-to-skin keeps the baby warm, stabilises breathing and heart rate, and helps breastfeeding. Dry the baby first; tie and cut the cord with clean equipment.</li>
        <li>Do not bathe the baby in the first 24 hours. Wrap the baby and avoid drafts to prevent cold.</li>
        <li>Danger signs need urgent care: difficulty breathing; not feeding; convulsions; very hot or cold; yellow palms or soles (jaundice).</li>
    </ul>
</div>

<h2>Right after birth</h2>
<ul>
    <li>Dry the baby with a clean cloth; clear mouth/nose if needed.</li>
    <li>Place the baby skin-to-skin on the mother’s chest.</li>
    <li>Tie and cut the cord with clean equipment.</li>
    <li>Encourage breastfeeding within 1 hour (helps womb contract and gives colostrum).</li>
</ul>

<h2>Keep warm</h2>
<ul>
    <li>Do not bathe the baby in the first 24 hours.</li>
    <li>Wrap the baby (including head) in a clean, dry cloth; avoid drafts.</li>
    <li>Skin-to-skin is one of the best ways to keep the baby warm.</li>
</ul>

<h2>Danger signs – seek care at once</h2>
<ul>
    <li>Difficulty breathing; not feeding or very weak sucking.</li>
    <li>Convulsions or fits; body very hot or cold.</li>
    <li>Yellow palms or soles (jaundice); lethargy or floppiness.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/questions-and-answers/item/early-essential-newborn-care">www.who.int – Early essential newborn care</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/healthy-lifestyle/infant-and-toddler-health/basics/newborn-health/hlv-20049400">www.mayoclinic.org – Newborn health</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/002395.htm">medlineplus.gov – Changes in the newborn at birth</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Essential care for the first day of life: skin-to-skin, warmth, breastfeeding, danger signs. WHO, Mayo Clinic, NIH.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Maternal Health"
            ),
            Article(title = "Infant Feeding (0–24 Months)", content = """<div class="key-facts"><h2>Key facts</h2><ul><li>WHO recommends exclusive breastfeeding for the first 6 months, with breastfeeding started within 1 hour of birth.</li><li>Exclusive breastfeeding protects against diarrhoea and infection and reduces infant mortality.</li><li>From 6 months, introduce complementary foods while continuing breastfeeding up to 2 years or beyond.</li><li>Complementary foods should be safe, adequate, and given responsively (following the child’s hunger and fullness).</li></ul></div><h2>From birth to 6 months – exclusive breastfeeding</h2><p><b>What it means:</b> Only breast milk—no water, formula, juice, or other foods. No pacifiers or bottles in the first weeks if possible.</p><p><b>Benefits:</b> Best nutrition; protects against diarrhoea and infection; good for the mother’s health and bonding.</p><p><b>Tips:</b> Feed on demand day and night. Hold the baby in a good position; ensure a deep latch. Seek support from a health worker or counsellor if you have pain, cracked nipples, or concerns about low milk supply.</p><h2>From 6 months – complementary feeding</h2><p>When the baby is about 6 months old, breast milk alone is no longer enough. Start offering soft, nutritious foods <b>in addition to</b> breastfeeding.</p><h3>How often:</h3><ul><li>6–8 months: 2–3 meals per day; increase as the baby accepts more.</li><li>9–11 months: 3–4 meals per day.</li><li>12–24 months: 3–4 meals plus 1–2 nutritious snacks per day.</li></ul><h3>What to give:</h3><ul><li>Mashed or soft vegetables, fruits, beans, lentils, eggs, fish, meat, poultry. Include iron-rich and vitamin A–rich foods.</li><li>Progress from purees to mashed and finger foods by about 8 months, and family foods by 12 months.</li><li>Avoid sugary drinks and limit added sugar and salt.</li></ul><h3>Safety:</h3><ul><li>Wash hands and food; use clean utensils and safe water.</li><li>Watch for choking; avoid hard or small round foods (e.g. whole nuts) in young children.</li></ul><hr/><div class="sources"><h2>Sources</h2><div class="source-item"><div class="source-name">WHO – Infant and young child feeding</div><a href="https://www.who.int/news-room/fact-sheets/detail/infant-and-young-child-feeding">www.who.int/news-room/fact-sheets/detail/infant-and-young-child-feeding</a></div><div class="source-item"><div class="source-name">WHO – Complementary feeding</div><a href="https://www.who.int/health-topics/complementary-feeding">www.who.int/health-topics/complementary-feeding</a></div><div class="source-item"><div class="source-name">NIH – Infant and young child feeding (NCBI)</div><a href="https://www.ncbi.nlm.nih.gov/books/NBK596430/">www.ncbi.nlm.nih.gov/books/NBK596430</a></div></div>""", summary = "Infant feeding from birth to 24 months: exclusive breastfeeding, then complementary foods. WHO, NIH.", source = "WHO, NIH", category = "Maternal Health"),
            // VITAMIN A DEFICIENCY - English (expanded)
            Article(
                title = "Vitamin A Deficiency",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Vitamin A deficiency is a leading preventable cause of childhood blindness and increases the risk of severe infection and death. It is common in many countries in Africa and Asia.</li>
        <li>Early signs: night blindness (poor vision in dim light), dry eyes, Bitot spots. Severe deficiency can lead to corneal damage and permanent blindness.</li>
        <li>WHO recommends vitamin A supplementation for children 6–59 months in areas where deficiency is a public health problem—often every 6 months at clinic or during campaigns.</li>
        <li>Diet: eat orange and green vegetables (carrots, sweet potato, spinach), eggs, liver, and fortified foods when available.</li>
    </ul>
</div>

<h2>Prevention</h2>
<ul>
    <li>Take vitamin A supplements when offered at the clinic (every 6 months in many programmes).</li>
    <li>Eat orange and green vegetables (carrots, sweet potato, pumpkin, spinach), eggs, liver, mango, papaya.</li>
    <li>Breastfeed infants—breast milk is a good source of vitamin A.</li>
</ul>

<h2>Signs – when to seek care</h2>
<ul>
    <li>Night blindness; dry or cloudy eyes; repeated infections; slow growth.</li>
    <li>A health worker can assess and advise on supplements or treatment; high-dose vitamin A can correct deficiency when given under supervision.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/tools/elena/interventions/vitamina-children">www.who.int – Vitamin A supplementation in children</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/drugs-supplements-vitamin-a/art-20365945">www.mayoclinic.org – Vitamin A</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/002400.htm">medlineplus.gov – Vitamin A</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Vitamin A: why supplements and diet matter for children. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Nutrition"
            ),
            // ANAEMIA IN WOMEN AND CHILDREN - English (expanded)
            Article(
                title = "Anaemia in Women and Children",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Anaemia means too few red blood cells or low haemoglobin. It affects about 40% of children under 5, 37% of pregnant women, and 30% of women of reproductive age globally.</li>
        <li>Most common cause is iron deficiency (poor diet, blood loss). Other causes: malaria, intestinal worms, folate or vitamin B12 deficiency, chronic infections.</li>
        <li>Signs: tiredness, weakness, pale skin, shortness of breath, dizziness. In pregnancy it increases risk of prematurity and low birth weight; in children it can affect growth and learning.</li>
        <li>Prevention and treatment: iron-rich foods, iron supplements in pregnancy, deworming, treating malaria. Seek a test if very tired or pale.</li>
    </ul>
</div>

<h2>Causes</h2>
<ul>
    <li>Iron deficiency (poor diet, heavy periods, blood loss); malaria; intestinal worms.</li>
    <li>Poor diet low in iron, folate, or vitamin B12; chronic diseases; HIV or TB.</li>
</ul>

<h2>What to do</h2>
<ul>
    <li>Eat iron-rich foods: meat, beans, lentils, leafy greens (spinach, kale), fortified cereals.</li>
    <li>Take iron tablets in pregnancy if given; deworm when offered; treat malaria; take folate if advised.</li>
    <li>Seek a blood test if very tired, pale, short of breath, or dizzy—treatment depends on the cause.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/anaemia">www.who.int/news-room/fact-sheets/detail/anaemia</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/iron-deficiency-anemia/symptoms-causes/syc-20355034">www.mayoclinic.org – Iron deficiency anemia</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/anemia.html">medlineplus.gov – Anemia</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Anaemia: causes, prevention, and when to seek care. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Nutrition"
            ),
            // HEPATITIS A AND E (WATERBORNE) - English (expanded)
            Article(
                title = "Hepatitis A and E (Waterborne)",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Hepatitis A and E are viral liver infections spread mainly by contaminated water and food (faeces in water, uncooked or contaminated food). Hepatitis E is a major cause of waterborne outbreaks.</li>
        <li>Symptoms: jaundice (yellow skin and eyes), fever, tiredness, nausea, vomiting, dark urine, pale stools. Many people, especially children, have mild or no symptoms.</li>
        <li>There is no specific medicine; the body usually clears the virus. Rest, fluids, good nutrition, and no alcohol. Most people recover fully. Hepatitis A vaccine is available and effective.</li>
        <li>Prevention: safe water, handwashing, cooked food, avoid raw shellfish and unpeeled raw fruits/vegetables in high-risk areas.</li>
    </ul>
</div>

<h2>Prevention</h2>
<ul>
    <li>Use safe water for drinking and washing food; wash hands with soap after toilet and before eating.</li>
    <li>Cook food well; avoid raw shellfish; in endemic areas, peel or wash fruits and vegetables with safe water.</li>
    <li>Hepatitis A vaccine (when available) recommended for at-risk groups and travellers.</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>Rest; plenty of fluids; nutritious food; no alcohol or unnecessary medicines.</li>
    <li>Most people recover within weeks to months. Seek care for severe vomiting, confusion, prolonged jaundice, or signs of liver failure.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/hepatitis-a">www.who.int – Hepatitis A</a><br/>
        <a href="https://www.who.int/news-room/fact-sheets/detail/hepatitis-e">www.who.int – Hepatitis E</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/hepatitis-a/symptoms-causes/syc-20367007">www.mayoclinic.org – Hepatitis A</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/000278.htm">medlineplus.gov – Hepatitis A</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Waterborne hepatitis A and E: prevention and when to seek care. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            // MENINGITIS: SIGNS AND PREVENTION - English (expanded)
            Article(
                title = "Meningitis: Signs and Prevention",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Meningitis is infection and swelling of the membranes and fluid around the brain and spinal cord. It can be caused by bacteria, viruses, or other germs. Bacterial meningitis can be fatal within days without prompt treatment.</li>
        <li>Signs in adults and older children: sudden high fever, stiff neck, severe headache, nausea or vomiting, confusion, sensitivity to light, rash (in some types), sleepiness or difficulty waking.</li>
        <li>Signs in babies: high fever, constant crying, bulging soft spot (fontanelle), refusal to feed, vomiting, stiffness, extreme sleepiness or irritability.</li>
        <li>Seek emergency care immediately. Vaccination (when available) helps prevent some bacterial causes (e.g. meningococcal, pneumococcal).</li>
    </ul>
</div>

<h2>Signs in adults and older children</h2>
<ul>
    <li>Sudden high fever; stiff neck; severe headache; nausea or vomiting.</li>
    <li>Confusion; sensitivity to light; rash (in some types); sleepiness or difficulty waking.</li>
</ul>

<h2>Signs in babies</h2>
<ul>
    <li>High fever; constant crying; bulging soft spot (fontanelle); refusal to feed; vomiting.</li>
    <li>Body stiffness; extreme sleepiness or irritability. Symptoms can develop over hours.</li>
</ul>

<h2>What to do</h2>
<ul>
    <li>Seek emergency care immediately—bacterial meningitis can be fatal within days without antibiotics.</li>
    <li>Vaccination (when available) helps prevent some bacterial causes (e.g. meningococcal, pneumococcal).</li>
    <li>During outbreaks, follow official health advice.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/meningitis">www.who.int/news-room/fact-sheets/detail/meningitis</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/meningitis/symptoms-causes/syc-20350508">www.mayoclinic.org – Meningitis</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/meningitis.html">medlineplus.gov – Meningitis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Recognise meningitis and get emergency care. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            // MEASLES: SYMPTOMS AND VACCINATION - English (expanded)
            Article(
                title = "Measles: Symptoms and Vaccination",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Measles is a highly contagious viral disease spread by coughs, sneezes, or contact with nose or throat secretions. Symptoms usually start 7–14 days after exposure.</li>
        <li>Early symptoms: high fever, cough, runny nose, red watery eyes. Then a rash that starts on the face and spreads down the body. Tiny white spots (Koplik spots) may appear inside the mouth.</li>
        <li>Complications include pneumonia, diarrhoea, ear infections, blindness, encephalitis, and death—especially in children under 5, malnourished children, and pregnant women. Vitamin A can reduce severity.</li>
        <li>Two doses of measles-containing vaccine (MCV/MMR) are highly effective. Keep vaccination records; catch up if doses were missed.</li>
    </ul>
</div>

<h2>Symptoms</h2>
<ul>
    <li>High fever, cough, runny nose, red watery eyes; then blotchy red rash from face downward.</li>
    <li>Tiny white spots (Koplik spots) inside mouth can appear before rash. Complications: pneumonia, severe diarrhoea, ear infections, blindness, brain inflammation, death.</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Two doses of measles-containing vaccine (MCV/MMR)—keep to national schedule; catch up if missed.</li>
    <li>Keep children away from people with measles. If exposed and unvaccinated, ask a health worker about vaccination or immunoglobulin as soon as possible.</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>No specific cure. Rest, fluids, fever control. Vitamin A (as advised by health worker) reduces complications and death in children.</li>
    <li>Seek care for severe illness, difficulty breathing, or confusion.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/measles">www.who.int/news-room/fact-sheets/detail/measles</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/measles/symptoms-causes/syc-20374857">www.mayoclinic.org – Measles</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/measles.html">medlineplus.gov – Measles</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Measles: symptoms, vaccination, and when to seek care. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            // HIV: TESTING AND PREVENTION - English (expanded)
            Article(
                title = "HIV: Testing and Prevention",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>HIV attacks the immune system and is spread through blood, semen, vaginal fluids, and breast milk—not through casual contact, hugging, or sharing food. There is no cure, but with treatment people can live long, healthy lives.</li>
        <li>Testing: HIV tests are available at health facilities (blood or oral). Knowing your status lets you start treatment and protect others. People on effective treatment with undetectable viral load do not pass HIV to sexual partners.</li>
        <li>Prevention: use condoms; do not share needles; take PrEP (pre-exposure prophylaxis) if advised. Pregnant women: get tested and take treatment to prevent passing HIV to the baby.</li>
        <li>Treatment: antiretroviral therapy (ART) is recommended for everyone with HIV. Start and stay on treatment as advised.</li>
    </ul>
</div>

<h2>Testing</h2>
<ul>
    <li>HIV tests available at health facilities (blood or rapid tests). Knowing your status helps you get treatment and protect others.</li>
    <li>If you might have been exposed recently, ask about the right test and when to retest.</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Use condoms correctly every time; do not share needles or syringes.</li>
    <li>Take PrEP (daily medicine to prevent HIV) if a health worker recommends it.</li>
    <li>Pregnant women: get tested and take ART as prescribed to protect the baby.</li>
</ul>

<h2>Treatment</h2>
<ul>
    <li>Antiretroviral therapy (ART)—daily medicines—keeps people with HIV healthy and reduces transmission.</li>
    <li>Start treatment as soon as possible; take it every day as advised. Stay in care and have viral load checked when offered.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/hiv-aids">www.who.int/news-room/fact-sheets/detail/hiv-aids</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/hiv-aids/diagnosis-treatment/drc-20373531">www.mayoclinic.org – HIV/AIDS diagnosis and treatment</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/hiv.html">medlineplus.gov – HIV</a>
    </div>
</div>
                """.trimIndent(),
                summary = "HIV testing, prevention, and treatment. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            // LASSA FEVER: AWARENESS - English (expanded)
            Article(
                title = "Lassa Fever: Awareness",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Lassa fever is a viral haemorrhagic illness caused by Lassa virus. It is spread mainly by the multimammate rat (Mastomys), which carries the virus in urine and faeces. Found in West Africa, including Nigeria, Sierra Leone, Liberia, Guinea, Benin, Ghana, Mali, and Togo.</li>
        <li>People get infected by contact with contaminated food or household items, or by breathing in particles. Person-to-person spread can occur through blood and body fluids, especially in health care without proper precautions.</li>
        <li>About 80% of infections cause no or mild symptoms. When symptoms occur (usually 1–3 weeks after exposure): fever, headache, sore throat, muscle pain, vomiting, diarrhoea, cough. Severe cases: facial swelling, bleeding, shock, seizures. Pregnant women in the third trimester are at very high risk.</li>
        <li>No licensed vaccine yet. Prevention: store food in rodent-proof containers; keep the house clean; avoid contact with blood and body fluids of sick people. Seek care early if you have been in an affected area and feel unwell.</li>
    </ul>
</div>

<h2>How it spreads</h2>
<ul>
    <li>Contact with food or household items contaminated by rodent urine or faeces; breathing in particles.</li>
    <li>Person-to-person through blood and body fluids (e.g. in health care without proper precautions).</li>
</ul>

<h2>Symptoms</h2>
<ul>
    <li>Fever, headache, sore throat, muscle pain, vomiting, diarrhoea, cough, abdominal pain (usually 1–3 weeks after exposure).</li>
    <li>Severe: bleeding, facial swelling, shock, seizures, coma. Hearing loss in about one in four survivors (sometimes permanent). Pregnant women in third trimester at very high risk.</li>
</ul>

<h2>Prevention</h2>
<ul>
    <li>Store food in rodent-proof containers; dispose of garbage away from the house; keep the house clean; keep cats to deter rats.</li>
    <li>Avoid contact with blood and body fluids of sick people; health workers must use infection prevention measures.</li>
</ul>

<h2>What to do</h2>
<ul>
    <li>Seek care early if you have been in an affected area and develop fever or other symptoms—supportive care in hospital improves survival.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/health-topics/lassa-fever">www.who.int – Lassa fever</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/lassa-fever/about/index.html">www.cdc.gov/lassa-fever/about</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/hemorrhagicfevers.html">medlineplus.gov – Hemorrhagic fevers</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Lassa fever: how it spreads and how to reduce risk. WHO, CDC, NIH sources.",
                source = "WHO, CDC, NIH",
                category = "Infectious Diseases"
            ),
            // EBOLA: WHAT TO KNOW - English (expanded)
            Article(
                title = "Ebola: What to Know",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Ebola virus disease (EVD) is a severe, often fatal illness. It spreads through direct contact with blood or body fluids (vomit, urine, faeces, saliva, sweat, breast milk, semen) of infected people or animals, or contaminated objects.</li>
        <li>It does not spread through the air. Symptoms usually appear 2–21 days after exposure (average 8–10 days). People become contagious when symptoms start.</li>
        <li>Early signs: sudden fever, weakness, headache, muscle pain, sore throat, vomiting, diarrhoea, stomach pain. Can progress to rash, red eyes, and unexplained bleeding or bruising.</li>
        <li>Vaccines exist for one type (Zaire ebolavirus). Supportive care in a health facility improves survival. Follow official advice during outbreaks.</li>
    </ul>
</div>

<h2>How it spreads</h2>
<ul>
    <li>Contact with blood or body fluids of sick people or those who have died from Ebola.</li>
    <li>Contact with contaminated objects (clothing, bedding, needles). Contact with infected animals (e.g. bats, primates).</li>
</ul>

<h2>Symptoms</h2>
<ul>
    <li>Sudden fever, weakness, headache, muscle pain, vomiting, diarrhoea, rash, sometimes bleeding or bruising.</li>
    <li>Disease can progress quickly. Seek care at a health facility as soon as symptoms appear.</li>
</ul>

<h2>What to do</h2>
<ul>
    <li>Seek care at a health facility. Avoid touching sick people or bodies; wash hands often.</li>
    <li>Health workers and families must use strict infection prevention (gloves, safe burial). Follow official advice during outbreaks.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/ebola-virus-disease">www.who.int/news-room/fact-sheets/detail/ebola-virus-disease</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/infectious-diseases/expert-answers/can-ebola-spread-through-air/faq-20115575">www.mayoclinic.org – Ebola transmission</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ebola.html">medlineplus.gov – Ebola</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Ebola: symptoms, spread, and what to do during an outbreak. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Infectious Diseases"
            ),
            Article(title = "Heat Exhaustion and Heatstroke", content = """<div class="key-facts"><h2>Key facts</h2><ul><li>Heat exhaustion and heatstroke are caused by the body overheating, often with high humidity and strenuous activity.</li><li>Heat exhaustion can progress to heatstroke, which is life-threatening and requires emergency care.</li><li>Older adults, young children, and people who are sick or overweight are at higher risk.</li></ul></div><h2>Heat exhaustion – signs and treatment</h2><p><b>Symptoms:</b> Heavy sweating; cool, moist skin with goosebumps; weakness; dizziness; nausea; headache; muscle cramps; fast, weak pulse; fatigue.</p><p><b>What to do:</b> Move to a cooler place (shade or indoors). Stop activity. Drink cool water or drinks with electrolytes. Remove extra clothing. Lie down with legs slightly raised. Cool skin with wet cloths or a cool shower. If symptoms do not improve within about 1 hour, or they worsen, seek medical care.</p><h2>Heatstroke – medical emergency</h2><p><b>Symptoms:</b> Body temperature 40°C (104°F) or higher; confusion or slurred speech; loss of consciousness; hot, dry skin or changed sweating; nausea and vomiting; rapid breathing; racing heart; severe headache.</p><p class="warning"><b>⚠️ Heatstroke can quickly damage the brain, heart, kidneys and muscles. Get emergency help immediately.</b></p><p><b>While waiting for help:</b> Move the person to shade or a cool place. Remove excess clothing. Cool them with whatever is available: cool water, wet cloths or towels on head, neck, armpits and groin; fan; ice packs if available. Do not give fluids if the person is not fully awake.</p><h2>Prevention</h2><ul><li>Drink plenty of fluids; replace salt and minerals (e.g. through food or rehydration drinks).</li><li>Wear light, loose clothing and a hat.</li><li>Limit strenuous work or exercise during the hottest hours; take breaks in the shade.</li><li>Never leave anyone (especially children or pets) in a parked car.</li><li>Be extra careful if you are older, have young children, or have chronic illness.</li></ul><hr/><div class="sources"><h2>Sources</h2><div class="source-item"><div class="source-name">Mayo Clinic – Heat exhaustion / Heatstroke</div><a href="https://www.mayoclinic.org/diseases-conditions/heat-exhaustion/symptoms-causes/syc-20373250">mayoclinic.org/diseases-conditions/heat-exhaustion</a></div><div class="source-item"><div class="source-name">NIH MedlinePlus – Heat illness</div><a href="https://medlineplus.gov/heatillness.html">medlineplus.gov/heatillness</a></div><div class="source-item"><div class="source-name">WHO – Heat and health</div><a href="https://www.who.int/news-room/fact-sheets/detail/climate-change-heat-and-health">who.int/news-room/fact-sheets/detail/climate-change-heat-and-health</a></div></div>""", summary = "Heat exhaustion and heatstroke: signs, first aid, and prevention. Mayo Clinic, WHO, NIH.", source = "Mayo Clinic, WHO, NIH", category = "Emergency Care"),
            // SAFE FOOD IN HOT WEATHER - English (expanded)
            Article(
                title = "Safe Food in Hot Weather",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Bacteria that cause food poisoning multiply faster in heat. Food spoils quicker in hot weather; contaminated food causes diarrhoea and vomiting.</li>
        <li>Do not leave perishable food out for more than 2 hours (or 1 hour if very hot, e.g. above 32°C). Keep cold food cold and hot food hot.</li>
    </ul>
</div>

<h2>Do</h2>
<ul>
    <li>Cook food well; eat soon after cooking.</li>
    <li>Store leftovers in a cool place (or reheat well before eating). Wash hands and surfaces before preparing food.</li>
    <li>Keep raw and cooked foods separate; use safe water for washing food and hands.</li>
</ul>

<h2>Avoid</h2>
<ul>
    <li>Raw or undercooked meat, poultry, or eggs.</li>
    <li>Food left out for hours in the heat; food that looks or smells off.</li>
    <li>Dirty water for washing food or hands.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/food-safety">www.who.int/news-room/fact-sheets/detail/food-safety</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/healthy-lifestyle/nutrition-and-healthy-eating/expert-answers/food-safety/faq-20058500">www.mayoclinic.org – Food safety</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/foodsafety.html">medlineplus.gov – Food safety</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Keep food safe in hot weather to avoid illness. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Water & Sanitation"
            ),
            // BURNS: FIRST AID AT HOME - English (expanded)
            Article(
                title = "Burns: First Aid at Home",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Cool the burn under clean, cool (not ice-cold) running water for 10–20 minutes. Cover with a clean, dry cloth or non-stick dressing. Do not put butter, oil, or paste on the burn.</li>
    </ul>
</div>

<h2>Small burns – first aid</h2>
<ul>
    <li>Cool under clean running water for 10–20 minutes.</li>
    <li>Remove jewellery or tight items before swelling. Cover with a clean cloth or sterile dressing.</li>
    <li>Do not break blisters; do not stick cloth to the burn.</li>
</ul>

<h2>When to seek care</h2>
<ul>
    <li>Burns on face, hands, feet, joints, or groin; burn larger than the palm of the hand.</li>
    <li>Deep burns; blisters; child or pregnant woman burned; electrical or chemical burn.</li>
    <li>Signs of infection: redness spreading, pus, fever, increased pain. Get a tetanus shot if needed (e.g. if last dose was over 5 years ago).</li>
</ul>

<h2>Do not</h2>
<ul>
    <li>Break blisters; use ice or very cold water; put butter, oil, or paste on the burn.</li>
    <li>Remove clothing stuck to the burn (leave it; cool over it).</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/burns">www.who.int/news-room/fact-sheets/detail/burns</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/first-aid/first-aid-burns/basics/art-20056649">www.mayoclinic.org – First aid burns</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/000030.htm">medlineplus.gov – Burns</a>
    </div>
</div>
                """.trimIndent(),
                summary = "First aid for burns and when to go to the clinic. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Emergency Care"
            ),
            // CUTS AND WOUNDS: WHEN TO STITCH - English (expanded)
            Article(
                title = "Cuts and Wounds: When to Stitch",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Press with a clean cloth to stop bleeding. Wash with clean water and soap. Cover with a clean dressing. For minor cuts, keep the wound clean and dry.</li>
        <li>Wounds that are deep, gaping, on the face or hand, or from a bite or dirty/rusty object often need medical care—stitches, tetanus shot, or antibiotics. Get care within 6–8 hours for dirty wounds; up to 12–24 hours for clean ones when closure is needed.</li>
    </ul>
</div>

<h2>First aid</h2>
<ul>
    <li>Wash your hands first. Press firmly with a clean cloth or gauze to stop bleeding.</li>
    <li>Wash the cut with clean running water and soap. Apply a clean dressing or bandage.</li>
    <li>Do not remove objects stuck deep in the wound—seek care.</li>
</ul>

<h2>When to seek care</h2>
<ul>
    <li>Deep or long cut; wound gaping or won’t stay closed; cut on face, hand, foot, or near a joint.</li>
    <li>Bite (animal or human); wound from dirty or rusty object; puncture wound.</li>
    <li>Bleeding that doesn’t stop after 10–15 minutes of pressure; spurting blood.</li>
    <li>Signs of infection: redness spreading, warmth, pus, fever, increased pain. You may need stitches, a tetanus shot, or antibiotics.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/teams/integrated-health-services/clinical-services-and-systems/emergency-and-critical-care/bec">www.who.int – Basic Emergency Care</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/first-aid/first-aid-cuts/basics/art-20056711">www.mayoclinic.org – Cuts and scrapes: First aid</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/000043.htm">medlineplus.gov – Cuts and puncture wounds</a>
    </div>
</div>
                """.trimIndent(),
                summary = "When to treat cuts at home and when to get stitches. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Emergency Care"
            ),
            // FEVER IN CHILDREN: WHEN TO WORRY - English (expanded)
            Article(
                title = "Fever in Children: When to Worry",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Fever is the body fighting infection. Most fevers in children are harmless and improve in a few days. A fever is often 38°C (100.4°F) or higher.</li>
        <li>Home care: give fluids, light clothing, paracetamol (or ibuprofen for older children) as advised for age. Watch for other signs.</li>
        <li>In malaria areas, fever can be malaria—get tested and treat as advised.</li>
    </ul>
</div>

<h2>Home care</h2>
<ul>
    <li>Give plenty of fluids; dress the child in light clothing.</li>
    <li>Paracetamol (acetaminophen) or ibuprofen as advised for age and weight—check the label. Do not give aspirin to children.</li>
    <li>Watch for other signs. If in doubt, contact a health worker.</li>
</ul>

<h2>Seek care urgently if</h2>
<ul>
    <li>Baby under 3 months with fever (any fever in a young infant needs immediate care).</li>
    <li>Child not drinking or very dehydrated; convulsion; fast or difficult breathing; very sleepy or hard to wake.</li>
    <li>Rash that does not fade when you press it (glass test); stiff neck; severe headache or abdominal pain.</li>
    <li>Fever lasting more than 3–5 days or getting worse despite treatment.</li>
</ul>

<h2>Malaria</h2>
<ul>
    <li>In malaria areas, fever can be malaria. Get tested at a health facility and treat as advised. Do not delay.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/maternal_child_adolescent/documents/IMCI_integrated_management_child_illness/en/">www.who.int – IMCI fever in children</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/fever/symptoms-causes/syc-20352759">www.mayoclinic.org – Fever</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – MedlinePlus</div>
        <a href="https://medlineplus.gov/ency/article/003090.htm">medlineplus.gov – Fever</a>
    </div>
</div>
                """.trimIndent(),
                summary = "When fever in a child needs a clinic or hospital. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Emergency Care"
            ),
            // STRESS AND YOUR HEALTH - English (expanded)
            Article(
                title = "Stress and Your Health",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Stress is a normal response to difficult or challenging situations. It can affect your body (tiredness, headaches, sleep problems), mood (worry, irritability, sadness), and behaviour (eating or sleeping more or less).</li>
        <li>When stress lasts a long time or is very strong, it can harm health. It is not a sign of weakness to ask for help.</li>
    </ul>
</div>

<h2>What is stress?</h2>
<ul>
    <li>Feeling overwhelmed, worried, or unable to cope. Can affect sleep, appetite, concentration, and health.</li>
</ul>

<h2>What helps</h2>
<ul>
    <li>Rest; talk to someone you trust; keep a regular routine; avoid too much alcohol and caffeine.</li>
    <li>Gentle activity (walking, stretching); slow breathing; time for relaxation or things you enjoy.</li>
    <li>Eat regularly, sleep enough, and stay connected with family or friends.</li>
</ul>

<h2>When to seek help</h2>
<ul>
    <li>If stress lasts a long time or you cannot do daily tasks, work, or care for your family.</li>
    <li>If you feel very sad, anxious, or think about hurting yourself—talk to a health worker or someone you trust. Help is available.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/questions-and-answers/item/stress">www.who.int – Stress</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/healthy-lifestyle/stress-management/basics/stress-relief/hlv-20049495">www.mayoclinic.org – Stress management</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NIMH</div>
        <a href="https://www.nimh.nih.gov/health/publications/so-stressed-out-fact-sheet">www.nimh.nih.gov – I’m so stressed out!</a>
    </div>
</div>
                """.trimIndent(),
                summary = "How stress affects health and what you can do. WHO, Mayo Clinic, NIH sources.",
                source = "WHO, Mayo Clinic, NIH",
                category = "Mental Health"
            ),
            // SIGNS OF DEPRESSION - English (expanded)
            Article(
                title = "Signs of Depression",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Depression is a common mental disorder. About 1 in 6 people will experience a major depressive episode in their lifetime.</li>
        <li>During a depressive episode, symptoms last most of the day, nearly every day, for at least 2 weeks.</li>
        <li>Depression is different from normal sadness—it affects daily life and can lead to serious outcomes, but it is treatable.</li>
        <li>Effective treatment exists: psychological treatment (e.g. talk therapy) and, when needed, medication. Getting help is a sign of strength.</li>
    </ul>
</div>

<h2>Common signs and symptoms</h2>
<ul>
    <li>Persistent sadness, irritability, or feeling empty</li>
    <li>Loss of interest or pleasure in activities you used to enjoy</li>
    <li>Feeling very tired or low in energy</li>
    <li>Changes in sleep (difficulty sleeping, waking early, or sleeping too much)</li>
    <li>Changes in appetite or unplanned weight changes</li>
    <li>Difficulty concentrating, remembering, or making decisions</li>
    <li>Feelings of hopelessness, worthlessness, or excessive guilt</li>
    <li>Thoughts about death or suicide (seek help immediately if this applies)</li>
</ul>

<h2>It is not your fault</h2>
<p>Depression is a health condition, not a weakness or a character flaw. It can affect anyone. It can be treated with support, therapy, and sometimes medicine.</p>

<h2>What to do</h2>
<ul>
    <li>Talk to a health worker or someone you trust. A doctor or nurse can screen for depression and refer you to care.</li>
    <li>Treatment may include psychological treatment (e.g. cognitive behavioural therapy), medication (antidepressants), or both.</li>
    <li>If you have thoughts of hurting yourself or ending your life, seek urgent help: go to the nearest health facility or emergency room, or contact a crisis helpline (e.g. 988 in the US; ask at your health facility for local numbers in Uganda and Togo).</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/depression">www.who.int/news-room/fact-sheets/detail/depression</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institute of Mental Health (NIMH)</div>
        <a href="https://www.nimh.nih.gov/health/publications/depression">www.nimh.nih.gov – Depression</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/depression/symptoms-causes/syc-20356007">www.mayoclinic.org – Depression</a>
    </div>
    <div class="source-item">
        <div class="source-name">NIH MedlinePlus</div>
        <a href="https://medlineplus.gov/depression.html">medlineplus.gov – Depression</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Recognise signs of depression and where to get help. WHO, NIMH, Mayo Clinic, MedlinePlus.",
                source = "WHO, NIMH, Mayo Clinic",
                category = "Mental Health"
            ),
            // WHEN TO SEEK MENTAL HEALTH HELP - English (expanded)
            Article(
                title = "When to Seek Mental Health Help",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Mental health problems are common. One in eight people globally lives with a mental disorder. Getting help is a sign of strength.</li>
        <li>Seek help if severe symptoms last 2 weeks or more, or if you cannot work, care for yourself or your family, or keep yourself safe.</li>
        <li>If you have thoughts of hurting yourself or ending your life, get urgent help: go to a health facility or call a crisis helpline.</li>
        <li>Treatment usually includes talk therapy and/or medication. Many people recover or learn to manage well with support.</li>
    </ul>
</div>

<h2>Seek help if</h2>
<ul>
    <li>You feel very sad, anxious, or irritable for weeks and it does not get better</li>
    <li>You cannot work, study, or care for your family as usual</li>
    <li>Your sleep, appetite, or energy are badly affected for a long time</li>
    <li>You have lost interest in things you used to enjoy</li>
    <li>You think about hurting yourself or ending your life—seek urgent care</li>
    <li>You hear or see things that others do not, or have beliefs that worry you or others</li>
    <li>You use alcohol or drugs to cope and it is causing problems</li>
</ul>

<h2>Where to get help</h2>
<ul>
    <li><b>Health centre or hospital:</b> Ask for mental health or counselling services. In Uganda and Togo, ask at your nearest facility—many offer basic mental health care or can refer you.</li>
    <li><b>Community or faith-based workers:</b> Some are trained to support mental health and can help you reach a health worker.</li>
    <li><b>Crisis support:</b> If you are in danger or thinking about suicide, go to the emergency room or contact a crisis helpline (e.g. 988 in the US; local numbers may be available—ask at a health facility).</li>
</ul>

<h2>You are not alone</h2>
<p>Mental health problems are common and can affect anyone. With the right support and treatment, many people recover or learn to manage well. Asking for help is a sign of strength.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/mental-health">www.who.int – Mental health</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institute of Mental Health (NIMH)</div>
        <a href="https://www.nimh.nih.gov/health/find-help">www.nimh.nih.gov – Find help for mental illnesses</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/mental-illness/diagnosis-treatment/drc-20374974">www.mayoclinic.org – Mental illness: diagnosis and treatment</a>
    </div>
</div>
                """.trimIndent(),
                summary = "When and where to get mental health support. WHO, NIMH, Mayo Clinic.",
                source = "WHO, NIMH, Mayo Clinic",
                category = "Mental Health"
            ),
            // BED NETS: USE AND CARE - English (expanded)
            Article(
                title = "Bed Nets: Use and Care",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Insecticide-treated nets (ITNs), especially long-lasting insecticidal nets (LLINs), are a core WHO-recommended tool to prevent malaria. They repel, kill, or reduce the ability of mosquitoes to bite.</li>
        <li>In high-transmission areas, all household members should sleep under a net every night—not only children and pregnant women. Community-wide use increases protection for everyone.</li>
        <li>Studies in Africa have shown that proper use of ITNs can reduce child deaths from malaria. Nets work best when used correctly and kept in good condition.</li>
        <li>Nets should be replaced when torn or after about 3 years (or as your national programme advises), as insecticide and fabric wear out.</li>
    </ul>
</div>

<h2>Why use a bed net</h2>
<p>Malaria is spread by mosquito bites, mainly at night. Insecticide-treated nets (ITNs) prevent bites and kill or repel mosquitoes. Using a net every night greatly reduces the risk of malaria for you and your family.</p>

<h2>How to use</h2>
<ul>
    <li><b>Hang the net</b> over the sleeping area so it covers the whole bed or sleeping mat.</li>
    <li><b>Tuck the net under the mattress or mat</b>—no gaps. Mosquitoes can get in through small openings.</li>
    <li><b>Everyone should sleep under the net</b>—children, pregnant women, and adults. One net per 1–2 people if possible.</li>
    <li><b>Use the net every night</b>, all year round in areas where malaria is present, not only in the rainy season.</li>
</ul>

<h2>Care and washing</h2>
<ul>
    <li><b>Do not wash the net too often.</b> Washing removes insecticide over time. Wash only when it is dirty (e.g. every few months).</li>
    <li><b>When washing:</b> Use cool or lukewarm water. Do not use soap—it can damage the insecticide. Rinse well. Dry the net in the shade (drying in direct sun can reduce insecticide).</li>
    <li><b>Do not wash or rub the net harshly;</b> gentle washing helps the net last longer.</li>
    <li><b>Replace the net</b> if it has many holes, is torn, or after about 3 years—or follow the advice of your local health programme. Get a new net from your health facility or distribution campaign when available.</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO)</div>
        <a href="https://www.who.int/groups/vector-control-advisory-group/summary-of-new-interventions-for-vector-control/insecticide-treated-nets">www.who.int – Insecticide-treated nets</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO – Key family practices (bednets)</div>
        <a href="https://www.emro.who.int/child-health/community-family/key-family-practices-on-child-health-care/Malaria-use-of-bednets.html">www.emro.who.int – Malaria: use of bednets</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/malaria/php/public-health-strategy/insecticide-treated-nets.html">www.cdc.gov – Insecticide-treated nets</a>
    </div>
    <div class="source-item">
        <div class="source-name">NIH MedlinePlus</div>
        <a href="https://medlineplus.gov/malaria.html">medlineplus.gov – Malaria</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Use and care of insecticide-treated mosquito nets for malaria prevention. WHO, CDC, NIH.",
                source = "WHO, CDC, NIH",
                category = "Infectious Diseases"
            ),
            // VACCINATION SCHEDULE (EPI) – UGANDA & TOGO - English (expanded)
            Article(
                title = "Vaccination Schedule (EPI) – Uganda & Togo",
                content = """
<div class="key-facts">
    <h2>Key facts</h2>
    <ul>
        <li>Uganda and Togo follow WHO-recommended schedules through their national Expanded Programme on Immunization (EPI). Vaccines are among the most effective ways to prevent serious illness and death in children.</li>
        <li>Routine vaccines typically include: BCG (tuberculosis), hepatitis B, polio, DTP (diphtheria, tetanus, pertussis), Hib, pneumococcal, rotavirus, measles (and often rubella), and in many areas yellow fever. Schedules may include additional vaccines (e.g. HPV for adolescents in Togo).</li>
        <li>Take your child at the ages your health facility recommends—commonly at birth, 6 weeks, 10 weeks, 14 weeks, 9 months, and 18 months. Exact dates and vaccines can vary; follow your local schedule and keep your vaccination card.</li>
        <li>If a dose was missed, catch-up is possible. Do not restart the series—take your child to the health facility and they will advise the next doses. Never skip a visit; catching up protects your child.</li>
    </ul>
</div>

<h2>Uganda & Togo EPI</h2>
<p>Both countries run an Expanded Programme on Immunization (EPI) aligned with WHO recommendations. Vaccines offered typically include:</p>
<ul>
    <li><b>BCG</b> – soon after birth (against tuberculosis)</li>
    <li><b>Hepatitis B</b> – often first dose at birth, then with other vaccines</li>
    <li><b>Polio</b> – oral (OPV) and/or inactivated (IPV) doses in early infancy and beyond</li>
    <li><b>DTP</b> – diphtheria, tetanus, pertussis (whooping cough), often with Hib and hepatitis B in combination</li>
    <li><b>Pneumococcal</b> – protects against serious chest and brain infections</li>
    <li><b>Rotavirus</b> – protects against severe diarrhoea in infants</li>
    <li><b>Measles</b> (and often <b>rubella</b>) – usually first dose around 9 months, second dose later</li>
    <li><b>Yellow fever</b> – in areas where it is recommended (e.g. parts of Uganda and Togo)</li>
</ul>
<p>Your health facility or national programme may add or adjust vaccines (e.g. HPV for girls). Ask at your nearest clinic for the current schedule.</p>

<h2>Take your child</h2>
<ul>
    <li>Common visit ages: <b>at birth</b>; <b>6 weeks, 10 weeks, 14 weeks</b>; <b>9 months</b>; <b>18 months</b>; and sometimes later for boosters (e.g. 4–7 years). Follow the schedule given by your health worker or on your card.</li>
    <li><b>Keep the vaccination card safe.</b> Bring it every time so the health worker can record doses and tell you when to come back.</li>
    <li>If you moved or missed a visit, take your child as soon as you can. The health worker will continue from where you left off; you usually do not need to restart.</li>
</ul>

<h2>Why it matters</h2>
<p>Vaccines prevent serious diseases such as measles, polio, tetanus, whooping cough, and tuberculosis. They protect your child and the community. Never skip a visit when possible, and catch up if doses were missed—every dose counts.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">World Health Organization (WHO) – Essential Programme on Immunization</div>
        <a href="https://www.who.int/teams/immunization-vaccines-and-biologicals/essential-programme-on-immunization">www.who.int – EPI</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO – Routine immunization summary tables</div>
        <a href="https://www.who.int/teams/immunization-vaccines-and-biologicals/policies/who-recommendations-for-routine-immunization---summary-tables">www.who.int – Routine immunization recommendations</a>
    </div>
    <div class="source-item">
        <div class="source-name">WHO Immunization Data – country schedules</div>
        <a href="https://immunizationdata.who.int/">immunizationdata.who.int</a>
    </div>
    <div class="source-item">
        <div class="source-name">CDC – Catch-up immunization schedule</div>
        <a href="https://www.cdc.gov/vaccines/schedules/hcp/imz/catchup.html">www.cdc.gov – Catch-up schedule</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Routine childhood vaccination in Uganda and Togo. WHO, UNICEF, CDC sources.",
                source = "WHO, UNICEF, CDC",
                category = "General Health"
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

<h2>👁️ Guide visuel : signes de déshydratation</h2>
<div style="background: linear-gradient(135deg, #E3F2FD 0%, #BBDEFB 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #1976D2;">
    <p style="margin-top: 0;"><b>À vérifier chez un enfant qui a la diarrhée :</b></p>
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">👄</span><br/><b>Bouche et lèvres</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Lèvres sèches, gercées = besoin de liquides</p>
            </td>
            <td style="padding: 8px; vertical-align: top; width: 50%;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">👁️</span><br/><b>Yeux</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Yeux enfoncés = déshydratation sévère</p>
            </td>
        </tr>
        <tr>
            <td style="padding: 8px; vertical-align: top;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">🖐️</span><br/><b>Pincement de peau</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Pincer la peau du ventre – si elle reste plissée = déshydratation</p>
            </td>
            <td style="padding: 8px; vertical-align: top;">
                <div style="text-align: center; padding: 8px; background: white; border-radius: 8px;"><span style="font-size: 28px;">🚽</span><br/><b>Urine</b></div>
                <p style="font-size: 13px; margin: 4px 0 0 0;">Urine foncée ou pas d'urine = boire plus / consulter</p>
            </td>
        </tr>
    </table>
</div>

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
            
            // TUBERCULOSE (TB) - French
            Article(
                title = "Tuberculose (TB)",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La TB est l'une des 10 principales causes de décès dans le monde et la première cause d'un seul agent infectieux.</li>
        <li>L'Afrique représente environ 25% des cas mondiaux de TB, avec des taux élevés de co-infection TB-VIH.</li>
        <li>La TB est guérissable avec un traitement approprié (généralement 6 mois d'antibiotiques).</li>
        <li>La TB se propage par l'air quand une personne infectée tousse, éternue ou parle.</li>
        <li>Toutes les personnes infectées ne tombent pas malades ; la TB latente peut être traitée pour prévenir la maladie active.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La tuberculose (TB) est causée par des bactéries qui affectent le plus souvent les poumons. Elle se propage par l'air quand les personnes atteintes de TB active toussent, éternuent ou crachent. La TB est évitable et guérissable, mais nécessite un diagnostic approprié et un traitement complet.</p>

<h2>Symptômes</h2>
<h3>Maladie TB active :</h3>
<ul>
    <li>Toux durant 3 semaines ou plus (parfois avec sang)</li>
    <li>Douleur thoracique</li>
    <li>Faiblesse ou fatigue</li>
    <li>Perte de poids</li>
    <li>Fièvre</li>
    <li>Sueurs nocturnes</li>
</ul>

<h2>Qui est à risque plus élevé ?</h2>
<ul>
    <li>Personnes vivant avec le VIH/SIDA (risque beaucoup plus élevé)</li>
    <li>Personnes malnutries</li>
    <li>Personnes diabétiques</li>
    <li>Fumeurs</li>
    <li>Enfants de moins de 5 ans</li>
</ul>

<h2>Dépistage</h2>
<p>La TB peut être diagnostiquée par :</p>
<ul>
    <li>Test des crachats (expectorations)</li>
    <li>Radiographie pulmonaire</li>
    <li>Test cutané (test tuberculinique)</li>
    <li>Tests sanguins</li>
</ul>
<p>Le dépistage est généralement gratuit dans les centres de santé.</p>

<h2>Traitement</h2>
<p class="warning"><b>⚠️ IMPORTANT :</b> Le traitement de la TB doit être complété entièrement (généralement 6 mois). Arrêter tôt mène à la TB résistante aux médicaments, beaucoup plus difficile à traiter.</p>
<ul>
    <li>Prendre tous les médicaments exactement comme prescrit</li>
    <li>Terminer le traitement complet même si vous vous sentez mieux</li>
    <li>Le traitement est généralement gratuit dans les établissements de santé publics</li>
    <li>La thérapie directement observée (DOT) aide à assurer l'achèvement</li>
</ul>

<h2>Prévention</h2>
<ul>
    <li>Vaccination : le vaccin BCG protège les enfants de la TB sévère</li>
    <li>Bonne ventilation : ouvrir les fenêtres, passer du temps à l'extérieur</li>
    <li>Couvrir la bouche en toussant ou éternuant</li>
    <li>Diagnostic et traitement précoces préviennent la propagation</li>
    <li>Pour les personnes vivant avec le VIH : le TAR réduit le risque de TB</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/tuberculosis">www.who.int/news-room/fact-sheets/detail/tuberculosis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "La TB est une cause majeure de décès dans le monde. Symptômes, dépistage, traitement (6 mois) et prévention incluant le vaccin BCG.",
                source = "OMS",
                category = "Maladies Infectieuses"
            ),
            
            // DENGUE - French
            Article(
                title = "Dengue",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La dengue est une infection virale transmise par les moustiques Aedes (mêmes moustiques qui transmettent Zika et chikungunya).</li>
        <li>La dengue se trouve dans les climats tropicaux et subtropicaux du monde entier, y compris certaines parties de l'Afrique.</li>
        <li>La plupart des personnes atteintes de dengue ont des symptômes légers ou aucun ; la dengue sévère peut être mortelle.</li>
        <li>Il n'y a pas de traitement spécifique pour la dengue ; la détection précoce et des soins médicaux appropriés sauvent des vies.</li>
        <li>La prévention se concentre sur l'évitement des piqûres de moustiques et l'élimination des sites de reproduction.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La dengue est causée par un virus transmis par la piqûre de moustiques Aedes infectés. Ces moustiques piquent pendant la journée, surtout tôt le matin et en fin d'après-midi. La dengue ne peut pas se propager directement de personne à personne.</p>

<h2>Symptômes</h2>
<h3>Dengue légère (fièvre dengue) :</h3>
<ul>
    <li>Fièvre élevée (40°C/104°F)</li>
    <li>Mal de tête sévère</li>
    <li>Douleur derrière les yeux</li>
    <li>Douleurs musculaires et articulaires</li>
    <li>Nausées, vomissements</li>
    <li>Glandes enflées</li>
    <li>Éruption cutanée</li>
</ul>
<p>Les symptômes durent généralement 2–7 jours.</p>

<h3 class="warning">⚠️ Dengue sévère (fièvre hémorragique dengue) – URGENCE :</h3>
<p>Signes d'alarme apparaissent 3–7 jours après les premiers symptômes :</p>
<ul>
    <li>Douleur abdominale sévère</li>
    <li>Vomissements persistants</li>
    <li>Respiration rapide</li>
    <li>Saignement des gencives ou du nez</li>
    <li>Sang dans les vomissements ou les selles</li>
    <li>Fatigue, agitation</li>
    <li>Peau froide et moite</li>
</ul>

<div style="background: linear-gradient(135deg, #FFEBEE 0%, #FFCDD2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #D32F2F;">
    <h3 style="margin-top: 0; color: #B71C1C;">⚠️ Dengue sévère – reconnaître ces signes</h3>
    <table style="width: 100%; border-collapse: collapse; font-size: 13px;">
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🤢</span><br/><b>Vomissements</b><br/>persistants</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🩸</span><br/><b>Saignements</b><br/>gencives, nez, selles</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😰</span><br/><b>Peau froide</b><br/>et moite</td>
        </tr>
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😣</span><br/><b>Douleur</b><br/>ventre sévère</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😮‍💨</span><br/><b>Respiration</b><br/>rapide</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😵</span><br/><b>Agitation</b><br/>ou faiblesse</td>
        </tr>
    </table>
    <p style="margin: 12px 0 0 0; font-weight: bold;">→ Aller à l'hôpital immédiatement</p>
</div>

<p class="warning"><b>⚠️ Consulter immédiatement si vous avez des signes d'alarme de dengue sévère.</b></p>

<h2>Traitement</h2>
<ul>
    <li>Pas de médicament antiviral spécifique pour la dengue</li>
    <li>Repos et boire beaucoup de liquides</li>
    <li>Utiliser du paracétamol pour la douleur et la fièvre</li>
    <li><b>Ne PAS utiliser d'aspirine ou d'ibuprofène</b> – peut augmenter le risque de saignement</li>
    <li>Pour la dengue sévère : soins hospitaliers avec liquides IV peuvent être nécessaires</li>
</ul>

<h2>Prévention</h2>
<h3>Éviter les piqûres de moustiques :</h3>
<ul>
    <li>Utiliser un répulsif (DEET, picaridine)</li>
    <li>Porter des chemises à manches longues et des pantalons longs</li>
    <li>Utiliser des moustiquaires (surtout pendant les siestes de jour)</li>
    <li>Garder les fenêtres et portes fermées ou protégées</li>
</ul>

<h3>Éliminer les sites de reproduction :</h3>
<ul>
    <li>Retirer l'eau stagnante (contenants, pneus, pots de fleurs)</li>
    <li>Couvrir les conteneurs de stockage d'eau</li>
    <li>Nettoyer les gouttières et les drains</li>
    <li>Changer l'eau dans les vases et bols d'animaux chaque semaine</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/dengue-and-severe-dengue">www.who.int/news-room/fact-sheets/detail/dengue-and-severe-dengue</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Dengue : infection virale transmise par les moustiques. Symptômes, signes d'alarme de dengue sévère, traitement et prévention.",
                source = "OMS",
                category = "Maladies Infectieuses"
            ),
            
            // FIÈVRE JAUNE - French
            Article(
                title = "Fièvre jaune",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La fièvre jaune est une maladie virale transmise par les moustiques, présente dans les zones tropicales d'Afrique et d'Amérique du Sud.</li>
        <li>La fièvre jaune peut causer une maladie sévère et la mort ; environ 15% des personnes atteintes de maladie sévère meurent.</li>
        <li>Un vaccin sûr et efficace existe ; une dose procure une protection à vie.</li>
        <li>De nombreux pays africains exigent un certificat de vaccination contre la fièvre jaune pour l'entrée.</li>
        <li>Il n'y a pas de traitement spécifique ; la prévention par vaccination et contrôle des moustiques est essentielle.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La fièvre jaune est causée par un virus transmis par des moustiques infectés. Le nom « fièvre jaune » vient de la jaunisse (jaunissement de la peau et des yeux) qui affecte certains patients. La maladie est endémique dans de nombreux pays africains.</p>

<h2>Symptômes</h2>
<p>Beaucoup de personnes n'ont pas de symptômes ou ont des symptômes légers. Quand les symptômes surviennent :</p>

<h3>Phase initiale (3–4 jours) :</h3>
<ul>
    <li>Fièvre</li>
    <li>Mal de tête</li>
    <li>Douleurs musculaires, surtout dans le dos</li>
    <li>Nausées, vomissements</li>
    <li>Perte d'appétit</li>
</ul>

<h3 class="warning">⚠️ Phase sévère (phase toxique) – affecte environ 15% :</h3>
<ul>
    <li>Fièvre élevée revient</li>
    <li>Jaunisse (peau et yeux jaunes)</li>
    <li>Urine foncée</li>
    <li>Douleur abdominale</li>
    <li>Saignement de la bouche, du nez, des yeux ou de l'estomac</li>
    <li>Vomissements de sang</li>
    <li>Insuffisance rénale</li>
</ul>
<p class="warning"><b>⚠️ La fièvre jaune sévère est une urgence médicale. Consulter immédiatement.</b></p>

<h2>Vaccination</h2>
<div class="highlight-box">
    <b>💉 Vaccin contre la fièvre jaune :</b><br/>
    • Une dose procure une protection à vie<br/>
    • Sûr et efficace<br/>
    • Généralement requis pour voyager vers/depuis les zones endémiques<br/>
    • Se faire vacciner au moins 10 jours avant le voyage<br/>
    • Gratuit ou à faible coût dans les centres de santé des pays endémiques
</div>

<h2>Qui devrait se faire vacciner ?</h2>
<ul>
    <li>Personnes vivant dans ou voyageant vers des zones à risque de fièvre jaune</li>
    <li>Nourrissons de 9 mois et plus (dans les zones endémiques)</li>
</ul>

<h3>Qui ne devrait PAS se faire vacciner (ou consulter un médecin d'abord) :</h3>
<ul>
    <li>Nourrissons de moins de 6 mois</li>
    <li>Personnes avec allergie sévère aux composants du vaccin</li>
    <li>Personnes avec système immunitaire affaibli</li>
    <li>Femmes enceintes (sauf risque élevé d'exposition)</li>
</ul>

<h2>Traitement</h2>
<ul>
    <li>Pas de traitement antiviral spécifique</li>
    <li>Soins de soutien : repos, liquides, soulagement de la douleur</li>
    <li>Soins hospitaliers peuvent être nécessaires pour les cas sévères</li>
</ul>

<h2>Prévention</h2>
<ul>
    <li><b>Se faire vacciner</b> – prévention la plus importante</li>
    <li>Éviter les piqûres de moustiques : utiliser un répulsif, porter des vêtements protecteurs</li>
    <li>Utiliser des moustiquaires</li>
    <li>Éliminer les sites de reproduction des moustiques (eau stagnante)</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/yellow-fever">www.who.int/news-room/fact-sheets/detail/yellow-fever</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Fièvre jaune : maladie virale transmise par les moustiques. Symptômes, signes de phase sévère, vaccination (protection à vie) et prévention.",
                source = "OMS",
                category = "Maladies Infectieuses"
            ),
            
            // SCHISTOSOMIASE (BILHARZIE) - French
            Article(
                title = "Schistosomiase (Bilharziose)",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La schistosomiase (bilharziose) est une maladie parasitaire causée par des vers qui vivent dans l'eau douce.</li>
        <li>Plus de 200 millions de personnes dans le monde ont besoin d'un traitement pour la schistosomiase ; la plupart vivent en Afrique.</li>
        <li>L'infection survient quand la peau entre en contact avec de l'eau douce contaminée (lacs, rivières, étangs).</li>
        <li>La schistosomiase est traitable avec une seule dose de praziquantel.</li>
        <li>La prévention se concentre sur l'évitement du contact avec l'eau contaminée et l'amélioration de l'assainissement.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La schistosomiase, aussi appelée bilharziose, est causée par des vers parasites. Les personnes sont infectées quand leur peau touche de l'eau douce contaminée par les parasites. Les vers pénètrent la peau et peuvent causer des dommages aux organes internes au fil du temps.</p>

<h2>Comment l'infection survient</h2>
<ol>
    <li>Les œufs de vers sont libérés dans l'urine ou les selles de personnes infectées</li>
    <li>Les œufs éclosent dans l'eau douce et infectent les escargots</li>
    <li>Les escargots infectés libèrent des larves dans l'eau</li>
    <li>Les larves pénètrent la peau humaine quand les gens nagent, se baignent ou travaillent dans l'eau contaminée</li>
    <li>Les vers mûrissent et pondent des œufs dans les vaisseaux sanguins</li>
</ol>

<h2>Symptômes</h2>
<h3>Symptômes précoces (dans les jours-semaines) :</h3>
<ul>
    <li>Éruption cutanée qui démange où les larves sont entrées</li>
    <li>Fièvre, frissons</li>
    <li>Toux</li>
    <li>Douleurs musculaires</li>
</ul>

<h3>Infection chronique (mois-années plus tard) :</h3>
<ul>
    <li>Sang dans l'urine (signe le plus courant)</li>
    <li>Sang dans les selles</li>
    <li>Douleur abdominale</li>
    <li>Diarrhée</li>
    <li>Foie ou rate agrandis</li>
    <li>Fatigue</li>
    <li>Chez les enfants : mauvaise croissance, difficultés d'apprentissage</li>
</ul>

<h2>Qui est à risque ?</h2>
<ul>
    <li>Personnes qui nagent, se baignent ou travaillent dans l'eau douce (rivières, lacs, étangs)</li>
    <li>Enfants jouant dans l'eau contaminée</li>
    <li>Agriculteurs et pêcheurs</li>
    <li>Personnes sans accès à l'eau potable et à l'assainissement</li>
</ul>

<h2>Traitement</h2>
<div class="highlight-box">
    <b>💊 Praziquantel :</b><br/>
    • Une seule dose ou un court traitement traite la schistosomiase<br/>
    • Sûr et efficace<br/>
    • Généralement gratuit par les programmes d'administration massive de médicaments<br/>
    • Consulter un professionnel de santé pour un diagnostic et traitement appropriés
</div>

<h2>Prévention</h2>
<ul>
    <li><b>Éviter le contact avec l'eau douce contaminée</b> – le plus important</li>
    <li>Utiliser des sources d'eau sûres pour se laver</li>
    <li>Faire bouillir ou filtrer l'eau si vous devez utiliser de l'eau de surface</li>
    <li>Porter des bottes protectrices si vous travaillez dans l'eau</li>
    <li>Améliorer l'assainissement : utiliser des latrines, ne pas uriner/déféquer dans l'eau</li>
    <li>Participer aux programmes d'administration massive de médicaments si disponibles</li>
</ul>

<h2>Pratiques d'eau sûre</h2>
<ul>
    <li>Stocker l'eau pendant 24–48 heures avant utilisation (les larves meurent)</li>
    <li>Chauffer l'eau à 50°C (122°F) tue les larves</li>
    <li>Filtrer l'eau à travers un tissu fin</li>
    <li>Utiliser de l'eau traitée/du robinet quand disponible</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/schistosomiasis">www.who.int/news-room/fact-sheets/detail/schistosomiasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Schistosomiase (bilharziose) : maladie parasitaire de l'eau douce contaminée. Symptômes, traitement avec praziquantel et prévention.",
                source = "OMS",
                category = "Maladies Infectieuses"
            ),
            
            // MALNUTRITION CHEZ LES ENFANTS - French
            Article(
                title = "Malnutrition chez les enfants",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La malnutrition contribue à environ 45% des décès chez les enfants de moins de 5 ans dans le monde.</li>
        <li>La malnutrition comprend à la fois la sous-nutrition (retard de croissance, émaciation, insuffisance pondérale) et les carences en micronutriments.</li>
        <li>La détection et le traitement précoces peuvent prévenir les problèmes de santé à long terme et la mort.</li>
        <li>L'allaitement maternel exclusif pendant 6 mois et la poursuite de l'allaitement avec des aliments complémentaires sont cruciaux.</li>
        <li>La surveillance régulière de la croissance aide à identifier la malnutrition tôt.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La malnutrition signifie qu'un enfant ne reçoit pas assez de nutriments ou le bon équilibre de nutriments. Elle peut causer un retard de croissance (taille faible pour l'âge), l'émaciation (poids faible pour la taille), l'insuffisance pondérale et des carences en vitamines et minéraux. La malnutrition affaiblit le système immunitaire et augmente le risque d'infections.</p>

<h2>Types de malnutrition</h2>
<h3>1. Retard de croissance (taille faible pour l'âge) :</h3>
<ul>
    <li>L'enfant est trop petit pour son âge</li>
    <li>Souvent causé par une mauvaise nutrition à long terme</li>
    <li>Peut affecter le développement du cerveau</li>
</ul>

<h3>2. Émaciation (poids faible pour la taille) :</h3>
<ul>
    <li>L'enfant est trop mince pour sa taille</li>
    <li>Souvent causé par une pénurie alimentaire sévère récente ou une maladie</li>
    <li>Risque élevé de décès</li>
</ul>

<h3>3. Insuffisance pondérale (poids faible pour l'âge) :</h3>
<ul>
    <li>L'enfant pèse trop peu pour son âge</li>
    <li>Peut être due au retard de croissance, à l'émaciation, ou aux deux</li>
</ul>

<h3>4. Carences en micronutriments :</h3>
<ul>
    <li>Manque de vitamines (A, D) ou minéraux (fer, zinc, iode)</li>
    <li>Peut causer anémie, cécité nocturne, immunité affaiblie</li>
</ul>

<h2>Signes et symptômes</h2>
<ul>
    <li>Ne prend pas de poids ou perd du poids</li>
    <li>Bras et jambes minces, côtes visibles</li>
    <li>Ventre, pieds ou visage enflés</li>
    <li>Peau sèche et squameuse</li>
    <li>Cheveux fins et cassants</li>
    <li>Manque d'énergie, apathie</li>
    <li>Infections fréquentes</li>
    <li>Développement retardé (n'atteint pas les étapes)</li>
</ul>

<h2>👁️ Signes à repérer</h2>
<div style="background: linear-gradient(135deg, #FFF3E0 0%, #FFE0B2 100%); border-radius: 12px; padding: 16px; margin: 16px 0; border-left: 5px solid #E65100;">
    <table style="width: 100%; border-collapse: collapse; font-size: 13px;">
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">💪</span><br/><b>Bras/jambes minces</b><br/>Côtes visibles</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🫃</span><br/><b>Ventre enflé</b><br/>ou pieds, visage</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🪮</span><br/><b>Peau sèche</b><br/>Cheveux fins</td>
        </tr>
        <tr>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">😴</span><br/><b>Peu d'énergie</b><br/>Apathie</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">🤒</span><br/><b>Maladies</b><br/>fréquentes</td>
            <td style="padding: 8px; text-align: center;"><span style="font-size: 28px;">📉</span><br/><b>Ne grandit pas</b><br/>ou perd du poids</td>
        </tr>
    </table>
</div>

<h2 class="warning">⚠️ Malnutrition aiguë sévère (MAS) – URGENCE :</h2>
<p>Signes :</p>
<ul>
    <li>Très mince (émaciation sévère)</li>
    <li>Pieds, mains ou visage enflés (œdème)</li>
    <li>Très faible, incapable de manger</li>
    <li>Pas d'appétit</li>
</ul>
<p class="warning"><b>⚠️ La malnutrition sévère nécessite des soins médicaux immédiats. Les enfants peuvent mourir sans traitement.</b></p>

<h2>Prévention</h2>
<h3>Pour les nourrissons :</h3>
<ul>
    <li><b>Allaitement maternel exclusif</b> pendant les 6 premiers mois</li>
    <li>Continuer l'allaitement jusqu'à 2 ans avec des aliments complémentaires</li>
    <li>Commencer les aliments complémentaires à 6 mois (pas trop tôt, pas trop tard)</li>
</ul>

<h3>Pour les enfants :</h3>
<ul>
    <li>Donner une variété d'aliments : céréales, légumineuses, légumes, fruits, protéines</li>
    <li>Nourrir fréquemment : 3 repas + 2 collations par jour</li>
    <li>Assurer des portions adéquates</li>
    <li>Inclure des aliments riches en fer (viande, haricots, légumes verts foncés)</li>
    <li>Aliments riches en vitamine A (légumes et fruits orange)</li>
    <li>Bonne hygiène : se laver les mains, nettoyer la préparation des aliments</li>
</ul>

<h2>Traitement</h2>
<ul>
    <li><b>Léger à modéré :</b> Conseil nutritionnel, aliments thérapeutiques, suppléments en micronutriments</li>
    <li><b>Sévère :</b> Soins hospitaliers avec lait/formule thérapeutique, traitement des infections, réalimentation progressive</li>
    <li>Traiter les causes sous-jacentes (diarrhée, infections, parasites)</li>
    <li>Suivi régulier et surveillance de la croissance</li>
</ul>

<h2>Surveillance de la croissance</h2>
<div class="highlight-box">
    <b>📊 Contrôles de croissance réguliers :</b><br/>
    • Peser et mesurer les enfants régulièrement<br/>
    • Utiliser les courbes de croissance pour suivre les progrès<br/>
    • Comparer aux standards de croissance OMS<br/>
    • La détection précoce permet une intervention précoce
</div>

<h2>Quand consulter</h2>
<ul>
    <li>L'enfant ne prend pas de poids ou perd du poids</li>
    <li>Signes de malnutrition sévère (gonflement, minceur extrême)</li>
    <li>L'enfant refuse de manger ou boire</li>
    <li>Maladies fréquentes</li>
    <li>Étapes de développement retardées</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/malnutrition">www.who.int/news-room/fact-sheets/detail/malnutrition</a>
    </div>
    <div class="source-item">
        <div class="source-name">UNICEF</div>
        <a href="https://www.unicef.org/nutrition">www.unicef.org/nutrition</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Malnutrition chez les enfants : types (retard de croissance, émaciation), signes, prévention par allaitement et aliments divers, et traitement.",
                source = "OMS, UNICEF",
                category = "Santé de l'Enfant"
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
            
            // EAU POTABLE - French (traitement + stockage combinés)
            Article(
                title = "Eau potable : traitement et stockage sûr",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Plus de 2 milliards de personnes n'ont pas accès à une eau de boisson gérée en toute sécurité.</li>
        <li>L'eau contaminée transmet choléra, typhoïde, hépatites A et E, et maladies diarrhéiques.</li>
        <li>Le traitement et le stockage sûrs de l'eau à domicile réduisent les diarrhées et sauvent des vies.</li>
        <li>L'eau potable pourrait prévenir environ 400 000 décès par an.</li>
    </ul>
</div>

<h2>Traitement de l'eau à domicile</h2>
<h3>1. Ébullition (le plus efficace) :</h3>
<ul>
    <li>Porter l'eau à <b>ébullition soutenue</b> pendant au moins 1 minute (3 minutes au-dessus de 2 000 m d'altitude).</li>
    <li>Tue virus, bactéries et parasites.</li>
    <li>Laisser refroidir ; conserver dans un récipient propre et couvert.</li>
</ul>

<h3>2. Chloration :</h3>
<ul>
    <li>Comprimés de purification ou chlore liquide (ex. 2 gouttes d'eau de Javel par litre d'eau claire).</li>
    <li>Suivre les instructions du produit. Attendre 30 minutes avant de boire.</li>
    <li>Le chlore tue la plupart des bactéries et virus ; moins efficace contre certains parasites.</li>
</ul>

<h3>3. Autres options (si disponibles) :</h3>
<ul>
    <li><b>Désinfection solaire (SODIS) :</b> bouteilles en plastique transparent au soleil 6–8 h (ou 2 jours si nuageux).</li>
    <li><b>Filtres :</b> filtres certifiés ; suivre les instructions du fabricant.</li>
</ul>

<hr/>

<h2>Stockage sûr de l'eau</h2>
<p>Conserver l'eau traitée dans des récipients qui la protègent de la recontamination.</p>
<ul>
    <li>Récipient <b>propre et couvert</b>, avec robinet ou ouverture étroite (pas de mains ni de récipients sales à l'intérieur).</li>
    <li>Garder le récipient <b>au-dessus du sol</b>.</li>
    <li>Ne pas mettre les mains ou des ustensiles sales ; verser l'eau ou utiliser une louche propre.</li>
    <li>Laver et sécher le récipient régulièrement. Utiliser l'eau sous 1–2 jours si non traitée au chlore.</li>
</ul>

<hr/>

<h2>Hygiène des mains</h2>
<p>Se laver les mains au savon et à l'eau propre :</p>
<ul>
    <li>Avant de manger ou préparer les repas</li>
    <li>Après les toilettes</li>
    <li>Après avoir changé les couches ou nettoyé un enfant</li>
    <li>Après avoir touché des animaux</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/fr/news-room/fact-sheets/detail/drinking-water">www.who.int/fr/news-room/fact-sheets/detail/drinking-water</a>
    </div>
    <div class="source-item">
        <div class="source-name">OMS – Traitement et stockage sûrs de l'eau à domicile</div>
        <a href="https://www.who.int/teams/environment-climate-change-and-health/water-sanitation-and-health/water-safety-and-quality/household-water-treatment-and-safe-storage">who.int/.../household-water-treatment-and-safe-storage</a>
    </div>
    <div class="source-item">
        <div class="source-name">CDC – Traitement et stockage de l'eau à domicile</div>
        <a href="https://www.cdc.gov/global-water-sanitation-hygiene/about/about-household-water-treatment.html">www.cdc.gov/global-water-sanitation-hygiene</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Eau potable : traitement (ébullition, chlore, filtres) et stockage sûr à domicile. OMS, CDC.",
                source = "OMS, UNICEF, CDC",
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

<h2>👁️ Guide visuel rapide</h2>
<div style="background: #f5f5f5; border-radius: 12px; padding: 16px; margin: 16px 0;">
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px;">
                <div style="text-align: center;"><span style="font-size: 36px;">🩹</span></div>
                <div style="text-align: center; font-weight: bold;">Saignement</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Appuyer fermement avec un tissu propre. Élever le membre au-dessus du cœur.</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px;">
                <div style="text-align: center;"><span style="font-size: 36px;">💧</span></div>
                <div style="text-align: center; font-weight: bold;">Brûlures</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Refroidir à l'eau propre 10–20 min. Pas de beurre ni d'huile.</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 33%; background: white; border-radius: 8px;">
                <div style="text-align: center;"><span style="font-size: 36px;">🫳</span></div>
                <div style="text-align: center; font-weight: bold;">Étouffement</div>
                <p style="font-size: 12px; margin: 4px 0 0 0;">Derrière la personne. Poussées vers le haut au-dessus du nombril.</p>
            </td>
        </tr>
    </table>
</div>

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
            
            // SANTÉ MENSTRUELLE - French
            Article(
                title = "Santé menstruelle",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Les règles sont une partie normale de la santé reproductive ; les cycles durent généralement 21 à 35 jours.</li>
        <li>Des saignements abondants, des douleurs fortes ou des cycles irréguliers peuvent signaler un problème de santé.</li>
        <li>Une bonne hygiène (matériel propre, lavage) réduit le risque d'infection.</li>
        <li>La douleur peut souvent être soulagée par le repos, la chaleur et des antalgiques (ex. ibuprofène).</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La santé menstruelle comprend des règles régulières et gérables et l'accès à l'information, aux produits et aux soins. Beaucoup de femmes ont des douleurs (crampes), des changements d'humeur ou de la fatigue pendant les règles ; c'est souvent normal mais peut parfois nécessiter un avis médical.</p>

<h2>Quand consulter un professionnel de santé</h2>
<ul>
    <li>Saignements très abondants (serviette ou tampon trempé toutes les 1–2 heures)</li>
    <li>Douleurs intenses qui ne s'améliorent pas avec le repos ou un antalgique</li>
    <li>Règles qui durent plus de 7 jours ou qui reviennent plus souvent que tous les 21 jours</li>
    <li>Absence de règles depuis plus de 3 mois (et vous n'êtes pas enceinte)</li>
    <li>Fièvre ou pertes malodorantes pendant les règles</li>
</ul>

<h2>Auto-soins</h2>
<ul>
    <li>Utiliser des matériaux absorbants propres (serviettes, tissu ou coupes) et les changer régulièrement</li>
    <li>Se laver les mains avant et après avoir changé les protections</li>
    <li>Se reposer et appliquer de la chaleur (ex. bouillotte) pour les crampes</li>
    <li>Manger régulièrement et s'hydrater</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/sexual-and-reproductive-health">www.who.int – Santé sexuelle et reproductive</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Santé menstruelle : cycles normaux, quand consulter, hygiène et auto-soins pour les crampes et les saignements abondants.",
                source = "OMS",
                category = "Santé générale"
            ),
            
            // MIGRAINES - French
            Article(
                title = "Migraines",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>La migraine est un trouble de la tête fréquent, souvent d'un côté et pulsatile, durant des heures à des jours.</li>
        <li>Beaucoup ont aussi des nausées, une sensibilité à la lumière ou au bruit, ou des troubles visuels (aura).</li>
        <li>Les facteurs déclenchants peuvent être le stress, le manque de sommeil, certains aliments, les lumières vives ou les changements hormonaux.</li>
        <li>Le repos dans une pièce sombre et calme et les antalgiques peuvent aider ; les maux de tête sévères ou nouveaux nécessitent un médecin.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La migraine est plus qu'un simple mal de tête. Elle provoque souvent une douleur modérée à sévère d'un côté de la tête, parfois avec nausées, vomissements et sensibilité à la lumière ou au bruit. Certaines personnes voient des flashs ou des lignes en zigzag (aura) avant la douleur.</p>

<h2>Facteurs déclenchants courants</h2>
<ul>
    <li>Stress ou anxiété</li>
    <li>Manque de sommeil ou sommeil irrégulier</li>
    <li>Repas sautés ou déshydratation</li>
    <li>Lumières vives, bruit fort ou odeurs fortes</li>
    <li>Certains aliments (ex. fromage affiné, chocolat, alcool)</li>
    <li>Changements hormonaux (ex. autour des règles)</li>
</ul>

<h2>Ce qui aide</h2>
<ul>
    <li>Se reposer dans une pièce sombre et calme</li>
    <li>Compresse froide ou chaude sur le front ou la nuque</li>
    <li>Antalgiques en vente libre (ex. ibuprofène, paracétamol) selon les indications</li>
    <li>Boire de l'eau et manger léger si possible</li>
</ul>

<p class="warning"><b>⚠️ Consulter si :</b> mal de tête soudain et très intense (« pire jamais vu »), fièvre ou raideur de la nuque, confusion ou faiblesse d'un côté du corps.</p>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/headache-disorders">www.who.int – Troubles de la tête</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Migraines : faits clés, déclencheurs, auto-soins et quand consulter pour les maux de tête.",
                source = "OMS",
                category = "Santé générale"
            ),
            
            // ALLERGIES - French
            Article(
                title = "Allergies",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Les allergies surviennent quand le système immunitaire réagit à une substance inoffensive (pollen, aliment, piqûre).</li>
        <li>Les symptômes légers : éternuements, nez qui coule, yeux ou peau qui démangent, éruption.</li>
        <li>Les réactions graves (anaphylaxie) peuvent provoquer gonflement, difficulté à respirer, malaise — urgence.</li>
        <li>Éviter le déclencheur et avoir un plan (antihistaminiques, adrénaline) peut prévenir ou traiter les réactions.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>Les réactions allergiques vont de légères (éternuements, démangeaisons, éruption) à potentiellement mortelles (gonflement de la gorge, difficulté à respirer). Déclencheurs courants : pollen, poussière, certains aliments (ex. noix, crustacés), piqûres, certains médicaments.</p>

<h2>Symptômes légers à modérés</h2>
<ul>
    <li>Éternuements, nez qui coule ou bouché</li>
    <li>Yeux qui démangent et larmoyants</li>
    <li>Peau qui démange ou urticaire (plaques rouges surélevées)</li>
    <li>Légers maux d'estomac (ex. après un aliment)</li>
</ul>
<p>Les antihistaminiques (conseillés par un pharmacien ou un médecin) et l'évitement du déclencheur aident souvent.</p>

<h2>Réaction grave (anaphylaxie) – urgence</h2>
<p>Signes : gonflement du visage, des lèvres ou de la gorge ; difficulté à respirer ou avaler ; sifflements ; faiblesse ou malaise soudain ; rythme cardiaque rapide.</p>

<h2>👁️ Légère vs grave en un coup d'œil</h2>
<div style="background: #f5f5f5; border-radius: 12px; padding: 16px; margin: 16px 0;">
    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="padding: 12px; vertical-align: top; width: 50%; background: #E8F5E9; border-radius: 8px;">
                <div style="text-align: center; font-weight: bold;">😊 Légère / modérée</div>
                <p style="font-size: 13px; margin: 8px 0 0 0;">🤧 Éternuements, nez qui coule<br/>👀 Yeux qui démangent<br/>🔴 Éruption, urticaire<br/>Les antihistaminiques aident souvent</p>
            </td>
            <td style="padding: 12px; vertical-align: top; width: 50%; background: #FFEBEE; border-radius: 8px;">
                <div style="text-align: center; font-weight: bold;">🚨 Grave (anaphylaxie)</div>
                <p style="font-size: 13px; margin: 8px 0 0 0;">😮 Gonflement visage/lèvres/gorge<br/>😮‍💨 Difficulté à respirer<br/>😵 Malaise, pouls faible<br/><b>→ Utiliser l'adrénaline, appeler les secours</b></p>
            </td>
        </tr>
    </table>
</div>

<p class="warning"><b>⚠️ Urgence médicale.</b> Utiliser un auto-injecteur d'adrénaline si prescrit, puis consulter en urgence.</p>

<h2>Prévention</h2>
<ul>
    <li>Identifier et éviter les déclencheurs connus (aliments, environnements, insectes)</li>
    <li>Avoir sur soi le médicament ou l'adrénaline prescrits en cas d'antécédents de réactions graves</li>
    <li>Informer l'entourage de la conduite à tenir en cas de réaction grave</li>
</ul>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/questions-and-answers/item/allergies">www.who.int – Allergies Q&R</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Allergies : symptômes légers vs graves, signes d'urgence d'anaphylaxie et prévention.",
                source = "OMS",
                category = "Santé générale"
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
            ),
            
            // TESTS RAPIDES - French
            Article(
                title = "Comment utiliser les tests rapides (COVID-19 et Paludisme)",
                content = """
<div class="highlight-box">
    <b>📋 Avant de commencer</b><br/>
    • Lisez la notice du kit – les marques peuvent varier.<br/>
    • Utilisez avant la date de péremption.<br/>
    • Conservez au sec et au frais. Gardez dans l'emballage aluminium jusqu'à l'utilisation.
</div>

<div class="key-facts">
    <h2>En bref</h2>
    <ul>
        <li>Les tests rapides donnent un résultat en 15–30 minutes à la maison ou au centre de santé.</li>
        <li>Les TDR paludisme détectent le parasite dans une goutte de sang.</li>
        <li>Les tests rapides COVID-19 détectent le virus à partir d’un prélèvement nasal ou gorge.</li>
        <li>Un résultat négatif n’élimine pas toujours l’infection – suivez les conseils sanitaires.</li>
    </ul>
</div>

<hr/>

<h2>🩸 Test rapide paludisme (TDR)</h2>

<h3>Ce qu’il vous faut</h3>
<ul>
    <li>Dispositif de test (sous sachet)</li>
    <li>Lancette (petite aiguille) ou autopiqueur</li>
    <li>Solution tampon (flacon ou compte-gouttes)</li>
    <li>Compresse alcool et mouchoir propre</li>
</ul>

<h3>Étapes</h3>
<ol>
    <li><b>Lavez les mains</b> au savon et séchez bien.</li>
    <li><b>Ouvrez le sachet</b> au moment de l’usage. Sortez le dispositif et posez-le sur une surface propre et plate.</li>
    <li><b>Désinfectez le doigt</b> (souvent annulaire ou majeur) avec la compresse alcool. Laissez sécher.</li>
    <li><b>Piquez le doigt</b> avec la lancette. Pressez légèrement pour obtenir une petite goutte de sang.</li>
    <li><b>Déposez le sang</b> dans le puits rond (zone échantillon) du dispositif – en général 1 goutte, ou selon la notice.</li>
    <li><b>Ajoutez le tampon</b> – le nombre de gouttes indiqué (souvent 2–4) dans le même puits ou le puits tampon.</li>
    <li><b>Attendez 15–20 minutes</b>. Ne touchez pas au dispositif. Utilisez un minuteur.</li>
    <li><b>Lisez le résultat</b> (voir ci-dessous). Ne pas lire après 30 minutes.</li>
</ol>

<h3>Comment lire le résultat</h3>
<div style="background: #E8F5E9; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #4CAF50;">
    <p style="margin: 0 0 8px 0;"><b>✅ NÉGATIF (pas de paludisme) :</b></p>
    <p style="margin: 0;">Une <b>seule bande</b> apparaît – dans la zone <b>Témoin (C)</b>. Le résultat est valide.</p>
</div>
<div style="background: #FFEBEE; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #D32F2F;">
    <p style="margin: 0 0 8px 0;"><b>⚠️ POSITIF (paludisme probable) :</b></p>
    <p style="margin: 0;"><b>Deux bandes</b> – une au <b>Témoin (C)</b> et une à la <b>Test (T)</b>. Consultez et suivez le traitement conseillé.</p>
</div>
<div style="background: #FFF8E1; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #F9A825;">
    <p style="margin: 0 0 8px 0;"><b>❌ Invalide :</b></p>
    <p style="margin: 0;">Aucune bande au <b>Témoin (C)</b>. Le test n’a pas fonctionné. Utilisez un nouveau kit.</p>
</div>

<p><b>Résumé :</b> C seule = négatif. C + T = positif. Pas de C = invalide.</p>

<hr/>

<h2>🦠 Test rapide antigénique COVID-19</h2>

<h3>Ce qu’il vous faut</h3>
<ul>
    <li>Dispositif de test (sous sachet)</li>
    <li>Écouvillon (long coton-tige)</li>
    <li>Solution tampon (tube avec bouchon)</li>
    <li>Minuteur</li>
</ul>

<h3>Étapes</h3>
<ol>
    <li><b>Lavez les mains</b> au savon et séchez. Mouchez-vous doucement, puis relavez les mains.</li>
    <li><b>Ouvrez le kit</b> et posez le dispositif sur une surface propre. Ouvrez le tube tampon sans renverser.</li>
    <li><b>Prélèvement</b> – Introduisez l’extrémité souple de l’écouvillon d’environ 2 cm dans une narine, appuyez doucement contre la paroi 10–15 secondes. Même chose dans l’autre narine avec le même écouvillon. (Certains kits utilisent gorge + nez – suivez la notice.)</li>
    <li><b>Mettez l’écouvillon dans le tampon</b> – Plongez l’écouvillon dans le tube, tournez ou pressez contre les parois pendant le temps indiqué (souvent 10–30 secondes). Pressez le tube et retirez l’écouvillon selon la notice.</li>
    <li><b>Déposez les gouttes sur le test</b> – Fermez le bouchon (s’il a un bec) ou utilisez la pipette. Ajoutez le nombre de gouttes indiqué sur le puits échantillon (S) du dispositif.</li>
    <li><b>Attendez</b> – En général 15–30 minutes. Ne déplacez pas le dispositif. Utilisez un minuteur.</li>
    <li><b>Lisez le résultat</b> (voir ci-dessous). Ne pas lire après le temps maximum indiqué (ex. 30 min).</li>
</ol>

<h3>Comment lire le résultat</h3>
<div style="background: #E8F5E9; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #4CAF50;">
    <p style="margin: 0 0 8px 0;"><b>✅ NÉGATIF (COVID-19 non détecté) :</b></p>
    <p style="margin: 0;">Une <b>seule bande</b> au <b>Témoin (C)</b>. Le test a fonctionné et le résultat est négatif au moment du test.</p>
</div>
<div style="background: #FFEBEE; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #D32F2F;">
    <p style="margin: 0 0 8px 0;"><b>⚠️ POSITIF (COVID-19 probable) :</b></p>
    <p style="margin: 0;"><b>Deux bandes</b> – une au <b>Témoin (C)</b> et une à la <b>Test (T)</b>. Même une bande faible en T = positif. Isolez-vous et suivez les consignes sanitaires.</p>
</div>
<div style="background: #FFF8E1; border-radius: 12px; padding: 16px; margin: 12px 0; border-left: 5px solid #F9A825;">
    <p style="margin: 0 0 8px 0;"><b>❌ Invalide :</b></p>
    <p style="margin: 0;">Aucune bande au <b>Témoin (C)</b>. Le test a échoué. Utilisez un nouveau kit.</p>
</div>

<p><b>Résumé :</b> C seule = négatif. C + T = positif. Pas de C = invalide.</p>

<hr/>

<h2>⚠️ Important</h2>
<ul>
    <li>Les tests rapides sont une aide, pas un remplacement du jugement d’un soignant.</li>
    <li>En cas de symptômes forts (forte fièvre, difficultés à respirer) avec test négatif, consultez quand même.</li>
    <li>Jetez lancettes et écouvillons en sécurité (conteneur fermé, hors de portée des enfants).</li>
</ul>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">OMS – Tests de diagnostic rapide du paludisme</div>
        <a href="https://www.who.int/fr/news-room/questions-and-answers/item/malaria-rapid-diagnostic-tests">OMS TDR Paludisme</a>
    </div>
    <div class="source-item">
        <div class="source-name">OMS – Tests de détection antigénique COVID-19</div>
        <a href="https://www.who.int/publications/i/item/WHO-2019-nCoV-Antigen_Detection-2021.1">OMS Tests antigéniques COVID-19</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Guide simple étape par étape pour les tests rapides paludisme et COVID-19. Résultats faciles à lire : une bande ou deux, positif et négatif.",
                source = "OMS",
                category = "Maladies Infectieuses"
            ),

            // === 30 articles pour Ouganda & Togo (français) ===
            Article(title = "Schistosomiase (Bilharziose)", content = """<h2>Principaux faits</h2><p>Fréquent en Ouganda et au Togo où l'on utilise les lacs et rivières. Causé par des parasites des mollusques.</p><h2>Symptômes</h2><p>Sang dans les urines, douleurs abdominales, diarrhée. Retard de croissance chez l'enfant.</p><h2>Prévention</h2><p>Éviter de nager ou de marcher dans l'eau douce en zone à risque. Utiliser de l'eau sûre.</p><h2>Traitement</h2><p>Médicament (praziquantel) en centre de santé. Consulter en cas de symptômes.</p>""", summary = "Schistosomiase : symptômes, prévention et traitement.", source = "OMS", category = "Maladies Infectieuses"),
            // Cécité des rivières (Onchocercose) - Français
            Article(
                title = "Cécité des rivières (Onchocercose)",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>L'onchocercose est causée par le ver parasite <i>Onchocerca volvulus</i>, transmis par la piqûre de simulies infectées qui se reproduisent près des rivières à courant rapide.</li>
        <li>Plus de 99 % des personnes infectées vivent en Afrique subsaharienne et au Yémen ; la maladie existe aussi en Amérique latine.</li>
        <li>Symptômes : démangeaisons intenses, lésions cutanées, atteinte oculaire pouvant mener à la cécité.</li>
        <li>Le traitement de masse à l'ivermectine (MDA) est la stratégie centrale ; prise au moins une fois par an pendant 10–15 ans en zone d'endémie.</li>
        <li>En 2024, plus de 171 millions de personnes ont été traitées dans le monde ; plusieurs pays ont été certifiés libres de la maladie.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>La transmission se fait par la piqûre d'une simulie infectée. Les microfilaires se développent dans l'organisme et provoquent des nodules sous la peau ; en mourant, elles déclenchent une inflammation intense (démangeaisons, lésions cutanées et oculaires, cécité).</p>

<h2>Symptômes</h2>
<ul>
    <li><b>Peau :</b> Démangeaisons intenses, éruptions, épaississement ou décoloration de la peau, nodules.</li>
    <li><b>Yeux :</b> Irritation, sensibilité à la lumière, lésions de la cornée, baisse de la vue, cécité (souvent après des années d'infection).</li>
</ul>

<h2>Prévention et traitement</h2>
<p><b>Traitement :</b> Prendre l'ivermectine lors des campagnes de traitement de masse (MDA). Ne pas interrompre le traitement—les communautés ont besoin d'un traitement soutenu pour éliminer la transmission.</p>
<p><b>Prévention :</b> Pas de vaccin. Réduire les piqûres : éviter les zones près des rivières aux heures de pic, manches longues, pantalons, répulsif si disponible.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/onchocerciasis">www.who.int/news-room/fact-sheets/detail/onchocerciasis</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/filarial-worms/about/onchocerciasis.html">www.cdc.gov/filarial-worms/about/onchocerciasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Cécité des rivières : transmission par simulies, symptômes et importance de l'ivermectine (MDA). Sources OMS et CDC.",
                source = "OMS, CDC",
                category = "Maladies Infectieuses"
            ),
            Article(title = "Filariose lymphatique (Éléphantiasis)", content = """<h2>Principaux faits</h2><p>Transmise par les moustiques. Peut causer gonflement des jambes, bras ou organes génitaux.</p><h2>Symptômes</h2><p>Gonflement, fièvre, douleur. Un traitement précoce évite l'infirmité.</p><h2>Prévention</h2><p>Prendre les médicaments en MDA quand proposés. Moustiquaires et protection contre les piqûres.</p>""", summary = "Éléphantiasis : prévention par la MDA et moustiquaires.", source = "OMS", category = "Maladies Infectieuses"),
            // Trachome - Français
            Article(
                title = "Trachome",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Le trachome est une infection oculaire bactérienne due à <i>Chlamydia trachomatis</i> et constitue la première cause infectieuse de cécité évitable dans le monde.</li>
        <li>Transmission par contact avec les sécrétions oculaires ou nasales (mains, vêtements, literie) et par les mouches en contact avec ces sécrétions.</li>
        <li>Les infections répétées provoquent des cicatrices de la face interne de la paupière ; les cils se tournent vers l'intérieur (trichiasis), grattent la cornée et mènent à la cécité.</li>
        <li>Environ 1,9 million de personnes sont aveugles ou malvoyantes ; 103 millions vivent en zone d'endémie. La stratégie SAFE (Chirurgie, Antibiotiques, Propreté du visage, Environnement) est utilisée pour l'élimination.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>Le trachome est contagieux et touche souvent les jeunes enfants. Au début : démangeaisons, irritation, paupières gonflées, écoulement purulent ou muqueux. L'évolution est lente ; le trichiasis (cils qui tournent vers l'œil) apparaît souvent à l'âge adulte. Les femmes sont plus touchées que les hommes.</p>

<h2>Symptômes</h2>
<ul>
    <li>Démangeaisons et irritation des yeux et des paupières</li>
    <li>Rougeur, douleur, sensibilité à la lumière</li>
    <li>Paupières gonflées, écoulement (mucus ou pus)</li>
    <li>Cicatrices de la paupière, cils qui se tournent vers l'œil (trichiasis)</li>
    <li>Opacité de la cornée et perte de vision</li>
</ul>

<h2>Prévention et traitement</h2>
<p><b>Stratégie SAFE :</b></p>
<ul>
    <li><b>C</b>hirurgie du trichiasis pour éviter la cécité.</li>
    <li><b>A</b>ntibiotiques (ex. azithromycine) lors des campagnes de traitement de masse.</li>
    <li><b>P</b>ropreté du visage : laver le visage des enfants et se laver les mains.</li>
    <li><b>E</b>nvironnement : eau propre, assainissement, réduction des mouches.</li>
</ul>
<p>Consulter en cas d'yeux qui démangent ou qui coulent, surtout en zone d'endémie.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/trachoma">www.who.int/news-room/fact-sheets/detail/trachoma</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/trachoma/symptoms-causes/syc-20378505">www.mayoclinic.org/diseases-conditions/trachoma</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Eye Institute (NIH)</div>
        <a href="https://www.nei.nih.gov/about/our-impact/nei-research-initiatives/international-vision-research/improving-global-vision-path-eliminating-trachoma">www.nei.nih.gov – Trachoma elimination</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Trachome : première cause infectieuse de cécité évitable. Symptômes, stratégie SAFE. Sources OMS, Mayo Clinic, NIH.",
                source = "OMS, Mayo Clinic, NIH",
                category = "Maladies Infectieuses"
            ),
            // Helminthiases (Vers intestinaux) - Français
            Article(
                title = "Helminthiases (Vers intestinaux)",
                content = """
<div class="key-facts">
    <h2>Principaux faits</h2>
    <ul>
        <li>Les helminthiases transmises par le sol (HTS) sont des vers parasites de l'intestin. Environ 1,5 milliard de personnes sont infectées, surtout en zones tropicales et subtropicales à assainissement insuffisant.</li>
        <li>Principaux types : ascaris, trichocéphale, ankylostomes. Transmission par les œufs dans les selles qui contaminent le sol ; les larves d'ankylostomes pénètrent la peau (marcher pieds nus).</li>
        <li>Les enfants infectés ont souvent un retard nutritionnel et physique ; l'ankylostome aggrave l'anémie chez les femmes en âge de procréer.</li>
        <li>Médicaments efficaces : albendazole 400 mg ou mébendazole 500 mg. L'OMS recommande la vermifugation périodique en zone d'endémie. Prévention : latrines, lavage des mains, chaussures, eau propre.</li>
    </ul>
</div>

<h2>Vue d'ensemble</h2>
<p>Les œufs des vers sont évacués dans les selles et contaminent le sol en l'absence d'assainissement. L'infection se fait en avalant des œufs (mains, eau, légumes mal lavés ou cuits) ou, pour l'ankylostome, par pénétration des larves dans la peau (pieds nus). Pas de transmission directe de personne à personne ; les œufs doivent mûrir environ 3 semaines dans le sol. L'oxyurose (vers communs chez l'enfant) se transmet par ingestion ou inhalation d'œufs à partir des mains ou des surfaces.</p>

<h2>Symptômes</h2>
<ul>
    <li>Douleurs abdominales, diarrhée (le trichocéphale peut causer la dysenterie)</li>
    <li>Anémie (surtout avec l'ankylostome)</li>
    <li>Malnutrition, retard de croissance, fatigue</li>
    <li>Oxyurose : démangeaisons anales ou vaginales (surtout la nuit), sommeil agité ; souvent sans symptôme</li>
</ul>

<h2>Prévention et traitement</h2>
<p><b>Traitement :</b> Prendre le vermifuge (albendazole ou mébendazole) quand il est distribué à l'école, en journée de santé ou au centre. Pour l'oxyurose, traiter toute la famille si besoin.</p>
<p><b>Prévention :</b> Se laver les mains après les toilettes et avant les repas ; utiliser des latrines ; porter des chaussures ; bien laver et cuire les légumes ; eau potable. Ongles courts, ne pas se gratter la zone anale. Laver draps et sous-vêtements à l'eau chaude en cas d'oxyurose.</p>

<hr/>

<div class="sources">
    <h2>Sources</h2>
    <div class="source-item">
        <div class="source-name">Organisation mondiale de la Santé (OMS)</div>
        <a href="https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections">www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections</a>
    </div>
    <div class="source-item">
        <div class="source-name">Centers for Disease Control and Prevention (CDC)</div>
        <a href="https://www.cdc.gov/sth/about/index.html">www.cdc.gov/sth/about</a>
    </div>
    <div class="source-item">
        <div class="source-name">Mayo Clinic (Oxyurose)</div>
        <a href="https://www.mayoclinic.org/diseases-conditions/pinworm/symptoms-causes/syc-20376382">www.mayoclinic.org/diseases-conditions/pinworm</a>
    </div>
    <div class="source-item">
        <div class="source-name">National Institutes of Health (NIH) – NCBI</div>
        <a href="https://www.ncbi.nlm.nih.gov/books/NBK560525/">www.ncbi.nlm.nih.gov/books – Helminthiasis</a>
    </div>
</div>
                """.trimIndent(),
                summary = "Vers intestinaux (ascaris, trichocéphale, ankylostome) : symptômes, vermifugation, prévention. Sources OMS, CDC, Mayo Clinic, NIH.",
                source = "OMS, CDC, Mayo Clinic, NIH",
                category = "Maladies Infectieuses"
            ),
            Article(title = "Lavage des mains : quand et comment", content = """<h2>Quand se laver les mains</h2><p>Avant de manger ou préparer les repas ; après les toilettes ; après avoir nettoyé un enfant.</p><h2>Comment</h2><p>Savon et eau propre. Frotter au moins 20 secondes. Sécher avec un linge propre.</p><h2>Sans savon</h2><p>Cendres ou eau seule. Le savon est le meilleur pour éviter diarrhées et infections.</p>""", summary = "Quand et comment se laver les mains.", source = "OMS", category = "Eau et Assainissement"),
            Article(title = "Hémorragie du postpartum : signes de danger", content = """<h2>Qu'est-ce que c'est ?</h2><p>Saignement abondant après l'accouchement. Cause majeure de décès maternel.</p><h2>Signes de danger</h2><p>Plus d'une serviette trempée par heure ; gros caillots ; vertiges ; pouls rapide.</p><h2>Que faire</h2><p>Urgence immédiate. Allonger la femme ; la garder au chaud. À l'hôpital : ocytocine et soins.</p>""", summary = "Reconnaître l'hémorragie après l'accouchement.", source = "OMS", category = "Santé Maternelle"),
            Article(title = "Soins du nouveau-né : premières 24 heures", content = """<h2>À la naissance</h2><p>Sécher le bébé ; contact peau à peau ; couper le cordon avec matériel propre ; allaiter dans l'heure.</p><h2>Chaleur</h2><p>Pas de bain les 24 premières heures. Couvrir le bébé.</p><h2>Signes de danger</h2><p>Difficulté à respirer ; ne tète pas ; convulsions ; très chaud ou froid. Consulter tout de suite.</p>""", summary = "Soins essentiels du premier jour de vie.", source = "OMS", category = "Santé Maternelle"),
            Article(title = "Alimentation du nourrisson (0–24 mois)", content = """<div class="key-facts"><h2>Principaux faits</h2><ul><li>L'OMS recommande l'allaitement maternel exclusif pendant les 6 premiers mois, en commençant dans l'heure qui suit la naissance.</li><li>L'allaitement exclusif protège contre la diarrhée et les infections et réduit la mortalité infantile.</li><li>À partir de 6 mois, introduire des aliments de complément tout en poursuivant l'allaitement jusqu'à 2 ans ou plus.</li><li>Les aliments de complément doivent être sûrs, adaptés et donnés de façon responsive (selon la faim et la satiété de l'enfant).</li></ul></div><h2>De la naissance à 6 mois – allaitement exclusif</h2><p><b>Qu'est-ce que c'est :</b> Seul le lait maternel — pas d'eau, de lait artificiel, de jus ni d'autres aliments.</p><p><b>Avantages :</b> Meilleure nutrition ; protection contre diarrhée et infections ; bon pour la santé de la mère et le lien.</p><p><b>Conseils :</b> Allaiter à la demande jour et nuit. Bonne position et prise du sein. Demander de l'aide (personnel de santé, conseillère) en cas de douleur, crevasses ou inquiétude sur la quantité de lait.</p><h2>À partir de 6 mois – alimentation de complément</h2><p>Vers 6 mois, le lait seul ne suffit plus. Proposer des aliments mous et nutritifs <b>en plus</b> du lait.</p><h3>Fréquence :</h3><ul><li>6–8 mois : 2–3 repas par jour.</li><li>9–11 mois : 3–4 repas par jour.</li><li>12–24 mois : 3–4 repas + 1–2 collations nutritives par jour.</li></ul><h3>Quoi donner :</h3><ul><li>Légumes, fruits, haricots, lentilles, œufs, poisson, viande en purée ou mou. Inclure aliments riches en fer et en vitamine A.</li><li>Passer des purées aux aliments en morceaux vers 8 mois, puis aliments familiaux vers 12 mois.</li><li>Éviter les boissons sucrées ; limiter sucre et sel ajoutés.</li></ul><h3>Sécurité :</h3><ul><li>Lavage des mains et des aliments ; ustensiles et eau propres.</li><li>Surveiller les fausses routes ; éviter aliments durs ou petits et ronds (ex. noix entières) chez le jeune enfant.</li></ul><hr/><div class="sources"><h2>Sources</h2><div class="source-item"><div class="source-name">OMS – Alimentation du nourrisson et du jeune enfant</div><a href="https://www.who.int/news-room/fact-sheets/detail/infant-and-young-child-feeding">www.who.int/.../infant-and-young-child-feeding</a></div><div class="source-item"><div class="source-name">OMS – Alimentation de complément</div><a href="https://www.who.int/health-topics/complementary-feeding">www.who.int/health-topics/complementary-feeding</a></div><div class="source-item"><div class="source-name">NIH – Alimentation du nourrisson (NCBI)</div><a href="https://www.ncbi.nlm.nih.gov/books/NBK596430/">www.ncbi.nlm.nih.gov/books/NBK596430</a></div></div>""", summary = "Alimentation de 0 à 24 mois : allaitement exclusif puis aliments de complément. OMS, NIH.", source = "OMS, NIH", category = "Santé Maternelle"),
            Article(title = "Carence en vitamine A", content = """<h2>Pourquoi c'est important</h2><p>Provoque mauvaise vision et infections plus graves chez l'enfant.</p><h2>Prévention</h2><p>Compléments en vitamine A au centre de santé. Manger légumes orange et verts, œufs.</p><h2>Signes</h2><p>Cécité nocturne ; yeux secs. Consulter.</p>""", summary = "Vitamine A : suppléments et alimentation.", source = "OMS", category = "Nutrition"),
            Article(title = "Anémie chez la femme et l'enfant", content = """<h2>Qu'est-ce que l'anémie ?</h2><p>Pas assez de globules rouges. Fréquent en grossesse et chez l'enfant. Fatigue, faiblesse.</p><h2>Causes</h2><p>Manque de fer, paludisme, vers, mauvaise alimentation.</p><h2>Que faire</h2><p>Aliments riches en fer (viande, haricots, légumes). Comprimés de fer en grossesse. Vermifuger. Traiter le paludisme.</p>""", summary = "Anémie : causes, prévention et soins.", source = "OMS", category = "Nutrition"),
            Article(title = "Hépatite A et E (hydrique)", content = """<h2>Principaux faits</h2><p>Transmises par eau et aliments contaminés. Jaunisse, fièvre, fatigue.</p><h2>Prévention</h2><p>Eau sûre ; lavage des mains ; aliments cuits.</p><h2>Traitement</h2><p>Repos ; boire. La plupart guérissent. Consulter si vomissements, confusion, jaunisse prolongée.</p>""", summary = "Hépatite hydrique : prévention et soins.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Méningite : signes et prévention", content = """<h2>Qu'est-ce que la méningite ?</h2><p>Infection des enveloppes du cerveau. Peut provoquer des épidémies. Urgent.</p><h2>Signes</h2><p>Nuque raide ; forte tête ; fièvre ; lumière gênante ; éruption ; confusion. Bébé : fontanelle bombée.</p><h2>Que faire</h2><p>Urgence immédiate. La vaccination aide à prévenir certaines formes.</p>""", summary = "Reconnaître la méningite et agir vite.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Rougeole : symptômes et vaccination", content = """<h2>Symptômes</h2><p>Fièvre, toux, nez qui coule, yeux rouges ; puis éruption. Peut causer pneumonie, décès.</p><h2>Prévention</h2><p>Vaccination (ROR). Deux doses. Tenir les enfants loin des malades.</p><h2>Traitement</h2><p>Pas de médicament spécifique. Soins de soutien ; vitamine A. Consulter si grave.</p>""", summary = "Rougeole : symptômes, vaccin et soins.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "VIH : dépistage et prévention", content = """<h2>Dépistage</h2><p>Tests VIH en centre de santé. Connaître son statut permet de se soigner et de protéger les autres.</p><h2>Prévention</h2><p>Préservatifs ; ne pas partager les aiguilles ; PrEP si conseillé. Femmes enceintes : dépistage et traitement pour protéger le bébé.</p><h2>Traitement</h2><p>Les antirétroviraux (ARV) gardent en bonne santé. Suivre le traitement.</p>""", summary = "VIH : dépistage, prévention et traitement.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Fièvre de Lassa : sensibilisation", content = """<h2>Qu'est-ce que c'est ?</h2><p>Maladie virale transmise par des rongeurs. Présente en Afrique de l'Ouest dont le Togo.</p><h2>Symptômes</h2><p>Fièvre, mal de tête, gorge, vomissements, diarrhée, saignements.</p><h2>Prévention</h2><p>Ranger la nourriture à l'abri des rongeurs ; maison propre. Éviter le contact avec sang et liquides des malades. Consulter tôt si zone à risque.</p>""", summary = "Fièvre de Lassa : transmission et prévention.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Ebola : à savoir", content = """<h2>Qu'est-ce qu'Ebola ?</h2><p>Maladie virale grave. Transmission par contact avec sang ou liquides des malades ou animaux.</p><h2>Symptômes</h2><p>Fièvre brutale, faiblesse, tête, vomissements, diarrhée, éruption, parfois saignements.</p><h2>Que faire</h2><p>Consulter à l'hôpital. Ne pas toucher les malades ou les corps. Se laver les mains. Suivre les consignes en épidémie.</p>""", summary = "Ebola : symptômes et conduite à tenir.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Épuisement et coup de chaleur", content = """<div class="key-facts"><h2>Principaux faits</h2><ul><li>L'épuisement par la chaleur et le coup de chaleur sont dus à une surchauffe du corps, souvent avec forte humidité et effort intense.</li><li>L'épuisement peut évoluer en coup de chaleur, qui met la vie en danger et exige des soins d'urgence.</li><li>Les personnes âgées, les jeunes enfants et les malades ou en surpoids sont plus à risque.</li></ul></div><h2>Épuisement par la chaleur – signes et traitement</h2><p><b>Symptômes :</b> Transpiration abondante ; peau fraîche et moite avec chair de poule ; faiblesse ; vertiges ; nausées ; mal de tête ; crampes ; pouls rapide et faible ; fatigue.</p><p><b>Que faire :</b> Mettre la personne au frais (ombre ou intérieur). Arrêter l'effort. Donner de l'eau fraîche ou des boissons avec électrolytes. Retirer les vêtements en trop. Allonger avec les jambes légèrement surélevées. Refroidir la peau avec linges mouillés ou douche fraîche. Si pas d'amélioration en environ 1 h, ou aggravation, consulter.</p><h2>Coup de chaleur – urgence médicale</h2><p><b>Symptômes :</b> Température ≥ 40 °C ; confusion ou parole difficile ; perte de conscience ; peau chaude et sèche ou transpiration anormale ; nausées, vomissements ; respiration rapide ; cœur qui s'emballe ; mal de tête sévère.</p><p class="warning"><b>⚠️ Le coup de chaleur peut endommager rapidement le cerveau, le cœur, les reins. Appeler les urgences immédiatement.</b></p><p><b>En attendant les secours :</b> Mettre la personne à l'ombre ou au frais. Retirer les vêtements en trop. La refroidir (eau fraîche, linges mouillés sur tête, cou, aisselles, aine ; ventilateur ; glace si possible). Ne pas donner à boire si la personne n'est pas bien consciente.</p><h2>Prévention</h2><ul><li>Boire suffisamment ; compenser le sel et les minéraux (alimentation, solutés de réhydratation).</li><li>Porter des vêtements légers et amples, un chapeau.</li><li>Limiter l'effort intense aux heures les plus chaudes ; faire des pauses à l'ombre.</li><li>Ne jamais laisser quelqu'un (surtout un enfant) dans une voiture en stationnement.</li><li>Être vigilant si on est âgé, avec de jeunes enfants, ou en mauvaise santé.</li></ul><hr/><div class="sources"><h2>Sources</h2><div class="source-item"><div class="source-name">Mayo Clinic – Épuisement / Coup de chaleur</div><a href="https://www.mayoclinic.org/diseases-conditions/heat-exhaustion/symptoms-causes/syc-20373250">mayoclinic.org/diseases-conditions/heat-exhaustion</a></div><div class="source-item"><div class="source-name">NIH MedlinePlus – Maladies liées à la chaleur</div><a href="https://medlineplus.gov/heatillness.html">medlineplus.gov/heatillness</a></div><div class="source-item"><div class="source-name">OMS – Chaleur et santé</div><a href="https://www.who.int/news-room/fact-sheets/detail/climate-change-heat-and-health">who.int/.../climate-change-heat-and-health</a></div></div>""", summary = "Épuisement et coup de chaleur : signes, premiers secours et prévention. Mayo Clinic, OMS, NIH.", source = "Mayo Clinic, OMS, NIH", category = "Soins d'Urgence"),
            Article(title = "Alimentation sûre par temps chaud", content = """<h2>Pourquoi</h2><p>Les aliments se gâtent vite à la chaleur. Aliments contaminés = diarrhée et vomissements.</p><h2>À faire</h2><p>Bien cuire ; manger rapidement ; garder les restes au frais. Mains et surfaces propres.</p><h2>À éviter</h2><p>Viande crue ; aliments laissés des heures ; eau sale pour laver.</p>""", summary = "Conserver les aliments en sécurité quand il fait chaud.", source = "OMS", category = "Eau et Assainissement"),
            Article(title = "Brûlures : premiers secours", content = """<h2>Petites brûlures</h2><p>Refroidir sous l'eau propre 10–20 min. Couvrir avec un linge propre. Pas de beurre ni pâte.</p><h2>Quand consulter</h2><p>Brûlure au visage, mains, articulations ; grande surface ; cloques ; enfant ou femme enceinte.</p><h2>Ne pas</h2><p>Percer les cloques ; coller le tissu sur la brûlure.</p>""", summary = "Premiers secours pour brûlures.", source = "OMS", category = "Soins d'Urgence"),
            Article(title = "Coupures et plaies : quand suturer", content = """<h2>Premiers secours</h2><p>Appuyer avec un linge propre pour arrêter le sang. Laver à l'eau et au savon. Pansement propre.</p><h2>Quand consulter</h2><p>Plaie profonde ou longue ; visage ou main ; morsure ; objet sale ou rouillé ; infection (rougeur, pus, fièvre). Points, vaccin antitétanique ou antibiotiques possibles.</p>""", summary = "Quand soigner à la maison et quand faire suturer.", source = "OMS", category = "Soins d'Urgence"),
            Article(title = "Fièvre chez l'enfant : quand s'inquiéter", content = """<h2>À la maison</h2><p>Donner à boire ; vêtements légers ; paracétamol selon l'âge. Surveiller.</p><h2>Consulter en urgence si</h2><p>Bébé de moins de 3 mois avec fièvre ; ne boit pas ; convulsion ; respiration rapide ; très somnolent ; éruption qui ne disparaît pas à la pression ; nuque raide.</p><h2>Paludisme</h2><p>En zone paludienne, la fièvre peut être le paludisme. Faire le test.</p>""", summary = "Quand la fièvre de l'enfant exige une consultation.", source = "OMS", category = "Soins d'Urgence"),
            Article(title = "Stress et santé", content = """<h2>Qu'est-ce que le stress ?</h2><p>Se sentir débordé, inquiet, incapable de faire face. Peut affecter le sommeil et l'appétit.</p><h2>Ce qui aide</h2><p>Repos ; parler à quelqu'un ; routine ; éviter trop d'alcool. Activité douce et respiration.</p><h2>Quand demander de l'aide</h2><p>Si le stress dure longtemps ou empêche les tâches quotidiennes, en parler à un soignant.</p>""", summary = "Impact du stress et quoi faire.", source = "OMS", category = "Santé Mentale"),
            Article(title = "Signes de dépression", content = """<h2>Signes courants</h2><p>Tristesse persistante ; perte d'intérêt ; fatigue ; changements de sommeil ou d'appétit ; difficulté à se concentrer ; désespoir.</p><h2>Ce n'est pas de votre faute</h2><p>La dépression est une maladie. Elle se soigne.</p><h2>Que faire</h2><p>Parler à un soignant ou à une personne de confiance. Soutien, thérapie ou médicaments possibles.</p>""", summary = "Reconnaître la dépression et où trouver de l'aide.", source = "OMS", category = "Santé Mentale"),
            Article(title = "Quand demander de l'aide en santé mentale", content = """<h2>Demander de l'aide si</h2><p>Vous vous sentez très triste ou anxieux pendant des semaines ; vous ne pouvez plus travailler ou vous occuper de la famille ; vous pensez à vous faire du mal ; vous entendez ou voyez des choses que les autres ne perçoivent pas.</p><h2>Où</h2><p>Centre de santé, hôpital ou travailleur communautaire. En Ouganda et au Togo, demander au centre le plus proche.</p><h2>Vous n'êtes pas seul</h2><p>Les problèmes de santé mentale sont courants. Demander de l'aide est une force.</p>""", summary = "Quand et où obtenir un soutien en santé mentale.", source = "OMS", category = "Santé Mentale"),
            Article(title = "Moustiquaires : utilisation et entretien", content = """<h2>Pourquoi</h2><p>Les moustiquaires imprégnées (MII) évitent le paludisme en empêchant les piqûres la nuit.</p><h2>Utilisation</h2><p>Rentrer la moustiquaire sous le matelas ; pas d'ouverture. Tout le monde sous la moustiquaire. Toutes les nuits.</p><h2>Entretien</h2><p>Ne pas laver trop souvent ; à l'eau froide sans savon si besoin. Remplacer si déchirée ou après 3 ans.</p>""", summary = "Utilisation et entretien des moustiquaires.", source = "OMS", category = "Maladies Infectieuses"),
            Article(title = "Calendrier vaccinal (PEV) – Ouganda et Togo", content = """<h2>PEV Ouganda et Togo</h2><p>Les deux pays suivent le calendrier OMS : BCG, polio, DTC, rougeole, fièvre jaune, etc. de la naissance à l'enfance.</p><h2>Emmener l'enfant</h2><p>À la naissance, 6, 10, 14 semaines ; 9 mois ; 18 mois (et selon le calendrier local). Garder le carnet.</p><h2>Pourquoi</h2><p>Les vaccins évitent des maladies graves. Ne pas sauter de visite ; rattraper si retard.</p>""", summary = "Vaccination systématique en Ouganda et au Togo.", source = "OMS/UNICEF", category = "Santé Générale")
        )
    }
}
