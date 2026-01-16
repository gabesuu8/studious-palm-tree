package com.example.helloapp.service

import com.example.helloapp.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URL

class ArticleFetcher {
    
    suspend fun fetchHealthcareArticles(): List<Article> = withContext(Dispatchers.IO) {
        val articles = mutableListOf<Article>()
        
        try {
            // Fetch from a healthcare RSS feed or website
            // Using WebMD RSS feed as an example
            val rssUrl = "https://www.webmd.com/rss/rss.aspx?RSSSource=RSS_PUBLIC"
            
            try {
                val doc = Jsoup.connect(rssUrl)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get()
                
                val items = doc.select("item")
                
                items.take(10).forEach { item ->
                    val title = item.select("title").text()
                    val link = item.select("link").text()
                    val description = item.select("description").text()
                    
                    // Try to fetch full content
                    val content = try {
                        if (link.isNotEmpty()) {
                            val contentDoc = Jsoup.connect(link)
                                .userAgent("Mozilla/5.0")
                                .timeout(10000)
                                .get()
                            contentDoc.select("article, .article-body, .content, p")
                                .text()
                                .take(2000) // Limit content length
                        } else {
                            description
                        }
                    } catch (e: Exception) {
                        description
                    }
                    
                    articles.add(
                        Article(
                            title = title,
                            content = content.ifEmpty { description },
                            summary = description.take(200),
                            source = "WebMD",
                            category = "General Health"
                        )
                    )
                }
            } catch (e: Exception) {
                // If RSS feed fails, add some sample articles
                articles.addAll(getSampleArticles())
            }
        } catch (e: Exception) {
            // Fallback to sample articles if network fails
            articles.addAll(getSampleArticles())
        }
        
        // If no articles fetched, return sample articles
        if (articles.isEmpty()) {
            articles.addAll(getSampleArticles())
        }
        
        articles
    }
    
    private fun getSampleArticles(): List<Article> {
        return listOf(
            Article(
                title = "Malaria: Symptoms, Prevention, and Treatment",
                content = """
                    OVERVIEW
                    
                    Malaria is a serious and sometimes fatal disease caused by a parasite that is transmitted 
                    to humans through the bite of infected Anopheles mosquitoes. In Africa, particularly in 
                    countries like Togo and Uganda, malaria is a major public health concern. This guide 
                    provides essential information to help you understand, prevent, and treat malaria.
                    
                    SYMPTOMS
                    
                    Malaria symptoms typically appear 10-15 days after being bitten by an infected mosquito. 
                    However, symptoms can appear as early as 7 days or as late as several months after infection. 
                    Common symptoms include:
                    
                    • High fever (often 38°C/100.4°F or higher)
                    • Chills and shivering
                    • Headache
                    • Muscle aches and fatigue
                    • Nausea and vomiting
                    • Diarrhea
                    • Sweating
                    • Anemia (pale skin, weakness)
                    
                    In severe cases, malaria can cause:
                    • Severe anemia
                    • Cerebral malaria (affecting the brain)
                    • Breathing problems
                    • Organ failure
                    • Coma
                    • Death
                    
                    Children under 5 years old and pregnant women are at highest risk for severe malaria.
                    
                    WHEN TO SEE A DOCTOR
                    
                    Seek immediate medical attention if you experience:
                    • High fever with chills
                    • Symptoms after traveling to or living in a malaria-endemic area
                    • Symptoms that don't improve with basic treatment
                    • Severe symptoms like confusion, seizures, or difficulty breathing
                    
                    Early diagnosis and treatment are crucial for preventing severe complications.
                    
                    CAUSES
                    
                    Malaria is caused by Plasmodium parasites. In Africa, the most common and dangerous type 
                    is Plasmodium falciparum. The parasite is transmitted when:
                    
                    1. An infected Anopheles mosquito bites a person
                    2. The parasite enters the bloodstream
                    3. The parasite travels to the liver and multiplies
                    4. The parasites re-enter the bloodstream and infect red blood cells
                    5. The infected blood cells burst, releasing more parasites
                    
                    RISK FACTORS
                    
                    Living in or traveling to malaria-endemic areas increases your risk. In Togo and Uganda, 
                    malaria is present year-round, with higher transmission during rainy seasons. Risk factors include:
                    
                    • Living in rural areas
                    • Lack of mosquito protection (bed nets, screens)
                    • Not taking preventive medication when recommended
                    • Being a young child or pregnant woman
                    • Having a weakened immune system
                    
                    PREVENTION
                    
                    Prevention is the best defense against malaria. Here are essential prevention strategies:
                    
                    1. USE INSECTICIDE-TREATED BED NETS (ITNs)
                       • Sleep under a bed net every night
                       • Ensure the net is properly tucked under the mattress
                       • Check for holes and repair them
                       • Re-treat nets with insecticide every 6-12 months
                       
                    2. INDOOR RESIDUAL SPRAYING (IRS)
                       • Support community-wide spraying programs
                       • Keep windows and doors closed during peak mosquito hours (dusk to dawn)
                       
                    3. WEAR PROTECTIVE CLOTHING
                       • Wear long-sleeved shirts and long pants, especially in the evening
                       • Use light-colored clothing (mosquitoes are attracted to dark colors)
                       
                    4. USE MOSQUITO REPELLENT
                       • Apply repellent containing DEET, picaridin, or oil of lemon eucalyptus
                       • Reapply as directed on the product label
                       • Use on exposed skin and clothing
                       
                    5. ELIMINATE MOSQUITO BREEDING SITES
                       • Remove standing water from containers, tires, and other items
                       • Cover water storage containers
                       • Clear gutters and drainage areas
                       • Fill in puddles and low-lying areas
                       
                    6. PREVENTIVE MEDICATION (CHEMOPROPHYLAXIS)
                       • Pregnant women: Take preventive medication as prescribed by your healthcare provider
                       • Travelers: Consult a doctor about preventive medication before traveling
                       • Follow the complete course of medication as directed
                       
                    7. COMMUNITY AWARENESS
                       • Participate in community health education programs
                       • Support local malaria control initiatives
                       • Report suspected cases to health authorities
                    
                    TREATMENT
                    
                    If you suspect you have malaria, seek medical treatment immediately. Treatment depends on:
                    • The type of malaria parasite
                    • The severity of symptoms
                    • Your age and pregnancy status
                    • Drug resistance in your area
                    
                    COMMON TREATMENT OPTIONS:
                    
                    1. ARTEMISININ-BASED COMBINATION THERAPY (ACT)
                       • First-line treatment for uncomplicated malaria
                       • Examples: Artemether-lumefantrine, Artesunate-amodiaquine
                       • Must complete the full course (usually 3 days)
                       
                    2. QUININE
                       • Used for severe malaria or when ACT is not available
                       • Usually given in a hospital setting
                       
                    3. SUPPORTIVE CARE
                       • Rest and fluids
                       • Fever management with paracetamol (acetaminophen)
                       • Blood transfusions if severe anemia occurs
                       
                    IMPORTANT: Always complete the full course of medication, even if you feel better. 
                    Stopping early can lead to treatment failure and drug resistance.
                    
                    PREGNANCY AND MALARIA
                    
                    Pregnant women are at high risk for severe malaria and complications. If you're pregnant:
                    • Sleep under an insecticide-treated bed net every night
                    • Take preventive medication as prescribed
                    • Seek immediate treatment if you develop symptoms
                    • Attend all prenatal care appointments
                    • Get tested for malaria during pregnancy
                    
                    CHILDREN AND MALARIA
                    
                    Children under 5 are especially vulnerable. Protect your children by:
                    • Ensuring they sleep under bed nets
                    • Dressing them in protective clothing
                    • Seeking immediate medical care for fever
                    • Completing all recommended vaccinations
                    • Following your healthcare provider's advice on preventive medication
                    
                    HOME CARE
                    
                    While waiting for medical care or during recovery:
                    • Rest in a comfortable, well-ventilated room
                    • Drink plenty of fluids (water, oral rehydration solution)
                    • Take paracetamol for fever (avoid aspirin in children)
                    • Monitor symptoms closely
                    • Keep the room cool to reduce fever
                    
                    COMPLICATIONS
                    
                    Untreated or improperly treated malaria can lead to:
                    • Severe anemia
                    • Cerebral malaria (brain infection)
                    • Respiratory distress
                    • Kidney failure
                    • Liver failure
                    • Death
                    
                    Prompt treatment significantly reduces the risk of complications.
                    
                    WHEN TO RETURN TO THE HOSPITAL
                    
                    Return immediately if you experience:
                    • Worsening symptoms after starting treatment
                    • New symptoms like confusion or seizures
                    • Difficulty breathing
                    • Severe weakness or inability to stand
                    • Signs of dehydration (dry mouth, no urination)
                    
                    MYTHS AND FACTS
                    
                    MYTH: Malaria is caused by bad air or dirty water.
                    FACT: Malaria is caused by a parasite transmitted by mosquitoes.
                    
                    MYTH: You can't get malaria twice.
                    FACT: You can get malaria multiple times. Each infection requires treatment.
                    
                    MYTH: Traditional medicine alone can cure malaria.
                    FACT: While some traditional remedies may help with symptoms, proper medical treatment 
                    with antimalarial drugs is essential.
                    
                    MYTH: Only people who live in rural areas get malaria.
                    FACT: Malaria can occur anywhere mosquitoes are present, including urban areas.
                    
                    RESOURCES AND SUPPORT
                    
                    In Togo and Uganda:
                    • Contact your local health center or clinic
                    • National Malaria Control Programs provide free bed nets and treatment
                    • Community health workers can provide guidance and testing
                    • Mobile health services may be available in rural areas
                    
                    Remember: Early diagnosis and treatment save lives. If you suspect malaria, don't wait—seek medical care immediately.
                    
                    This information is for educational purposes. Always consult with a qualified healthcare provider for diagnosis and treatment.
                """.trimIndent(),
                summary = "Comprehensive guide to malaria: symptoms, prevention, and treatment. Essential information for communities in Togo, Uganda, and across Africa.",
                source = "Mayo Clinic Style Guide",
                category = "Infectious Diseases"
            ),
            Article(
                title = "Understanding Your Blood Pressure",
                content = """
                    Blood pressure is the force of blood pushing against the walls of your arteries. 
                    It's measured in millimeters of mercury (mmHg) and recorded as two numbers:
                    
                    • Systolic pressure (top number): The pressure when your heart beats
                    • Diastolic pressure (bottom number): The pressure when your heart rests
                    
                    Normal blood pressure is typically around 120/80 mmHg. High blood pressure 
                    (hypertension) is 140/90 or higher. High blood pressure can lead to serious 
                    health problems like heart disease and stroke.
                    
                    To maintain healthy blood pressure:
                    - Eat a balanced diet low in sodium
                    - Exercise regularly
                    - Maintain a healthy weight
                    - Limit alcohol consumption
                    - Don't smoke
                    - Manage stress
                    
                    Regular check-ups with your doctor are important to monitor your blood pressure.
                """.trimIndent(),
                summary = "Learn about blood pressure, what the numbers mean, and how to maintain healthy levels.",
                source = "Health Guide",
                category = "Cardiovascular Health"
            ),
            Article(
                title = "The Importance of Regular Exercise",
                content = """
                    Regular physical activity is one of the most important things you can do for your health. 
                    Exercise helps control weight, reduces risk of heart disease, strengthens bones and muscles, 
                    and improves mental health.
                    
                    Benefits of regular exercise:
                    • Weight management
                    • Reduced risk of chronic diseases
                    • Improved mood and energy
                    • Better sleep
                    • Stronger bones and muscles
                    • Improved brain health
                    
                    The Centers for Disease Control and Prevention (CDC) recommends:
                    - At least 150 minutes of moderate-intensity aerobic activity per week
                    - Muscle-strengthening activities 2 or more days per week
                    
                    Examples of moderate-intensity activities:
                    - Brisk walking
                    - Bicycle riding
                    - Swimming
                    - Dancing
                    
                    Start slowly and gradually increase your activity level. Even small amounts of 
                    physical activity are better than none.
                """.trimIndent(),
                summary = "Discover the many health benefits of regular exercise and how to get started.",
                source = "Health Guide",
                category = "Fitness"
            ),
            Article(
                title = "Healthy Eating Basics",
                content = """
                    A healthy diet is essential for good health and nutrition. It protects you against 
                    many chronic diseases and helps maintain a healthy body weight.
                    
                    Key principles of healthy eating:
                    
                    1. Eat a variety of foods
                       Include fruits, vegetables, whole grains, lean proteins, and healthy fats.
                    
                    2. Control portion sizes
                       Pay attention to serving sizes to avoid overeating.
                    
                    3. Limit processed foods
                       Choose whole, unprocessed foods when possible.
                    
                    4. Stay hydrated
                       Drink plenty of water throughout the day.
                    
                    5. Balance your meals
                       Include protein, carbohydrates, and healthy fats in each meal.
                    
                    Foods to include:
                    • Fruits and vegetables (aim for 5 servings per day)
                    • Whole grains (brown rice, whole wheat bread, oats)
                    • Lean proteins (chicken, fish, beans, nuts)
                    • Healthy fats (avocado, olive oil, nuts)
                    
                    Foods to limit:
                    • Added sugars
                    • Saturated and trans fats
                    • Sodium
                    • Processed foods
                    
                    Remember, it's about balance and moderation, not perfection.
                """.trimIndent(),
                summary = "Learn the fundamentals of healthy eating and how to build a balanced diet.",
                source = "Health Guide",
                category = "Nutrition"
            ),
            Article(
                title = "Getting Quality Sleep",
                content = """
                    Sleep is essential for your physical and mental health. Most adults need 7-9 hours 
                    of quality sleep per night.
                    
                    Benefits of good sleep:
                    • Improved concentration and productivity
                    • Better immune function
                    • Lower risk of heart disease and diabetes
                    • Better mood and mental health
                    • Improved memory
                    
                    Tips for better sleep:
                    
                    1. Stick to a schedule
                       Go to bed and wake up at the same time every day, even on weekends.
                    
                    2. Create a bedtime routine
                       Relaxing activities like reading or taking a warm bath can help.
                    
                    3. Make your bedroom sleep-friendly
                       Keep it cool, dark, and quiet. Consider using blackout curtains.
                    
                    4. Limit screen time before bed
                       The blue light from screens can interfere with sleep.
                    
                    5. Avoid large meals and caffeine before bed
                       These can disrupt your sleep.
                    
                    6. Exercise regularly
                       But avoid intense exercise close to bedtime.
                    
                    7. Manage stress
                       Practice relaxation techniques like deep breathing or meditation.
                    
                    If you consistently have trouble sleeping, talk to your doctor. Sleep disorders 
                    like insomnia or sleep apnea may need medical attention.
                """.trimIndent(),
                summary = "Discover why sleep matters and learn practical tips for getting better rest.",
                source = "Health Guide",
                category = "Sleep Health"
            ),
            Article(
                title = "Managing Stress Effectively",
                content = """
                    Stress is a normal part of life, but chronic stress can take a toll on your health. 
                    Learning to manage stress effectively is important for your overall well-being.
                    
                    Effects of chronic stress:
                    • Headaches and muscle tension
                    • Sleep problems
                    • Digestive issues
                    • Weakened immune system
                    • Increased risk of heart disease
                    • Anxiety and depression
                    
                    Effective stress management techniques:
                    
                    1. Exercise regularly
                       Physical activity is a great stress reliever.
                    
                    2. Practice relaxation techniques
                       Try deep breathing, meditation, or yoga.
                    
                    3. Get enough sleep
                       Aim for 7-9 hours per night.
                    
                    4. Eat a healthy diet
                       Avoid excessive caffeine and sugar.
                    
                    5. Stay connected
                       Talk to friends and family for support.
                    
                    6. Set realistic goals
                       Break large tasks into smaller, manageable steps.
                    
                    7. Learn to say no
                       Don't overcommit yourself.
                    
                    8. Take breaks
                       Schedule regular breaks during your day.
                    
                    9. Practice time management
                       Prioritize tasks and avoid procrastination.
                    
                    10. Seek professional help if needed
                        If stress becomes overwhelming, consider talking to a therapist.
                    
                    Remember, it's okay to ask for help when you need it.
                """.trimIndent(),
                summary = "Learn practical strategies for managing stress and protecting your health.",
                source = "Health Guide",
                category = "Mental Health"
            )
        )
    }
}
