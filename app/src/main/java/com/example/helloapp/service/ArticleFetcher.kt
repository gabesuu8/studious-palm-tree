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
            )
        )
    }
}
