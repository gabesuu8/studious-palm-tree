package com.example.helloapp.service

import com.example.helloapp.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URL

class ArticleFetcher {
    
    suspend fun fetchHealthcareArticles(): List<Article> = withContext(Dispatchers.IO) {
        // Return offline articles for reliability
        // These are evidence-based articles with official source citations
        getSampleArticles()
    }
    
    private fun getSampleArticles(): List<Article> {
        return listOf(
            // MALARIA
            Article(
                title = "Malaria: Complete Guide for Prevention and Treatment",
                content = """
OVERVIEW

Malaria is a life-threatening disease caused by Plasmodium parasites transmitted through infected Anopheles mosquito bites. According to the World Health Organization (WHO), Africa carries the highest burden, with over 90% of global malaria cases.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SYMPTOMS

Symptoms typically appear 10-15 days after infection:

• High fever (38°C/100.4°F or higher)
• Chills and sweating
• Headache
• Muscle and joint pain
• Fatigue and weakness
• Nausea, vomiting, diarrhea
• Anemia (pale skin, weakness)

⚠️ DANGER SIGNS - Seek emergency care immediately:
• Confusion or seizures
• Difficulty breathing
• Severe weakness
• Dark or bloody urine
• Jaundice (yellow eyes/skin)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREVENTION

1. SLEEP UNDER INSECTICIDE-TREATED BED NETS
   - Use every night, even when it's hot
   - Tuck net under mattress completely
   - Check for and repair holes

2. ELIMINATE MOSQUITO BREEDING SITES
   - Empty standing water from containers
   - Cover water storage tanks
   - Clear gutters and drains

3. PROTECT YOURSELF
   - Wear long sleeves and pants at dusk/dawn
   - Use mosquito repellent (DEET, picaridin)
   - Keep doors and windows screened

4. FOR PREGNANT WOMEN
   - Take preventive medication (IPTp) as prescribed
   - Attend all prenatal visits
   - Sleep under treated nets every night

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TREATMENT

Seek medical care immediately if you suspect malaria. Treatment includes:

• Artemisinin-based Combination Therapy (ACT) - first-line treatment
• Complete the FULL course of medication (usually 3 days)
• Rest and drink plenty of fluids
• Take paracetamol for fever (NOT aspirin for children)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

To strengthen your body's defense against malaria:

🥗 NUTRITION
• Eat iron-rich foods (dark leafy greens, beans, meat)
• Include vitamin C foods (oranges, tomatoes, peppers)
• Stay well-hydrated with clean water

💪 PHYSICAL ACTIVITY
• Regular exercise strengthens immune system
• 30 minutes of walking daily
• Avoid outdoor exercise at dusk when mosquitoes are active

😴 REST & RECOVERY
• Get 7-8 hours of sleep
• Rest when feeling unwell
• Recover fully before resuming normal activities

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

This information is based on guidelines from:

📚 World Health Organization (WHO)
   www.who.int/malaria

📚 Centers for Disease Control and Prevention (CDC)
   www.cdc.gov/malaria

📚 Roll Back Malaria Partnership
   www.rollbackmalaria.org

📚 Uganda Ministry of Health
   www.health.go.ug

📚 Togo Ministry of Health
   www.sante.gouv.tg

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Early diagnosis and treatment save lives. If you have fever, get tested for malaria immediately at your nearest health facility.
                """.trimIndent(),
                summary = "WHO-approved guide to malaria prevention and treatment with lifestyle recommendations for Togo, Uganda, and Africa.",
                source = "WHO, CDC, Roll Back Malaria",
                category = "Infectious Diseases"
            ),
            
            // DIARRHEA
            Article(
                title = "Diarrhea and Dehydration: Life-Saving Treatment Guide",
                content = """
OVERVIEW

Diarrheal diseases are the second leading cause of death in children under 5 years old globally. According to the WHO, proper treatment with Oral Rehydration Solution (ORS) and zinc can prevent most deaths.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SYMPTOMS OF DEHYDRATION

Watch for these warning signs:

MILD DEHYDRATION:
• Thirst
• Dry mouth and lips
• Decreased urination
• Slightly sunken eyes

SEVERE DEHYDRATION (EMERGENCY):
• Very sunken eyes
• Unable to drink or drinks poorly
• Skin pinch goes back slowly (>2 seconds)
• Lethargy or unconsciousness
• No tears when crying
• No urination for 6+ hours

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TREATMENT: ORAL REHYDRATION THERAPY

HOW TO MAKE ORS AT HOME:
If you cannot get ORS packets, mix:
• 1 liter of clean (boiled and cooled) water
• 6 level teaspoons of sugar
• 1/2 level teaspoon of salt

HOW MUCH TO GIVE:
• Children under 2 years: 50-100ml after each loose stool
• Children 2-10 years: 100-200ml after each loose stool
• Older children and adults: As much as wanted

ZINC SUPPLEMENTS (for children):
• Children under 6 months: 10mg daily for 10-14 days
• Children over 6 months: 20mg daily for 10-14 days

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

WHEN TO SEEK MEDICAL HELP

Go to a health facility immediately if:
• Blood in stool
• Fever higher than 38.5°C
• Signs of severe dehydration
• Diarrhea lasting more than 3 days
• Child refuses to eat or drink
• Repeated vomiting

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREVENTION

1. SAFE WATER
   • Boil water before drinking
   • Store water in clean, covered containers
   • Use water purification tablets when available

2. HAND HYGIENE
   • Wash hands with soap:
     - Before eating
     - Before preparing food
     - After using the toilet
     - After changing diapers

3. FOOD SAFETY
   • Cook food thoroughly
   • Eat food while hot
   • Wash fruits and vegetables
   • Keep food covered

4. SANITATION
   • Use latrines/toilets
   • Dispose of feces safely
   • Keep living areas clean

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

🥗 NUTRITION DURING AND AFTER DIARRHEA
• Continue breastfeeding for infants
• Give small, frequent meals
• Offer bananas, rice, toast, potatoes
• Avoid fatty, spicy, or sugary foods
• Continue normal diet once recovered

💧 HYDRATION
• Drink clean water throughout the day
• Include soups and broths
• Avoid sugary drinks and sodas
• Coconut water is excellent for rehydration

🏠 HOME ENVIRONMENT
• Keep cooking areas clean
• Store food properly
• Maintain clean water sources
• Practice good hygiene daily

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/health-topics/diarrhoea

📚 UNICEF - Oral Rehydration Therapy
   www.unicef.org

📚 Centers for Disease Control and Prevention
   www.cdc.gov/healthywater

📚 Rehydration Project
   www.rehydrate.org

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Diarrhea can be deadly, but it's easily treated. ORS saves lives - give it early and give it often.
                """.trimIndent(),
                summary = "WHO-approved guide to treating diarrhea and preventing dehydration with ORS. Critical information for parents and caregivers.",
                source = "WHO, UNICEF, CDC",
                category = "Child Health"
            ),
            
            // CHOLERA
            Article(
                title = "Cholera: Prevention, Recognition, and Emergency Treatment",
                content = """
OVERVIEW

Cholera is an acute diarrheal infection caused by contaminated water or food. According to the WHO, cholera affects 1-4 million people annually with up to 143,000 deaths. It can kill within hours if untreated but is easily preventable and treatable.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SYMPTOMS

Cholera can range from mild to severe:

MILD TO MODERATE:
• Watery diarrhea
• Mild dehydration
• Thirst

SEVERE (EMERGENCY):
• Profuse watery diarrhea ("rice water" appearance)
• Vomiting
• Leg cramps
• Rapid dehydration
• Shock (weak pulse, low blood pressure)
• Sunken eyes, dry mouth
• Wrinkled skin on fingers

⚠️ Severe cholera can cause death within hours if not treated!

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

IMMEDIATE TREATMENT

1. START REHYDRATION IMMEDIATELY
   • Give ORS (Oral Rehydration Solution)
   • As much as the person can drink
   • Continue even if vomiting (give small sips)

2. SEEK MEDICAL CARE
   • Go to nearest health facility
   • Cholera Treatment Centers during outbreaks
   • IV fluids may be needed for severe cases

3. ANTIBIOTICS
   • Prescribed by health workers
   • Reduce duration and severity
   • Complete the full course

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREVENTION

1. SAFE WATER - "BOIL IT, FILTER IT, OR FORGET IT"
   • Boil water for at least 1 minute
   • Use water purification tablets
   • Drink only from safe sources

2. FOOD SAFETY
   • Cook food thoroughly
   • Eat food while hot
   • Avoid raw foods during outbreaks
   • Peel fruits yourself

3. HAND HYGIENE
   • Wash hands with soap frequently
   • Especially after toilet and before eating
   • Use ash or sand if soap unavailable

4. SANITATION
   • Use latrines properly
   • Never defecate near water sources
   • Dispose of waste safely

5. DURING OUTBREAKS
   • Avoid public gatherings
   • Get vaccinated if available
   • Report cases to health authorities

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

🥗 NUTRITION
• Eat freshly cooked, hot meals
• Include clean fruits and vegetables
• Maintain good nutrition for strong immunity
• Breastfeed infants exclusively for 6 months

💧 WATER PRACTICES
• Always treat water before drinking
• Keep water containers clean and covered
• Wash hands before handling water
• Don't share drinking cups during outbreaks

🏠 HOME HYGIENE
• Keep cooking areas clean
• Wash dishes with clean water
• Dispose of garbage properly
• Maintain clean latrines

👥 COMMUNITY ACTION
• Report suspected cases
• Participate in clean-up campaigns
• Support vaccination programs
• Share prevention knowledge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/health-topics/cholera

📚 Global Task Force on Cholera Control
   www.gtfcc.org

📚 Centers for Disease Control and Prevention
   www.cdc.gov/cholera

📚 Médecins Sans Frontières (MSF)
   www.msf.org/cholera

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Cholera is preventable. Safe water and good hygiene are your best protection. During outbreaks, act fast - rehydration saves lives.
                """.trimIndent(),
                summary = "Emergency guide to cholera prevention and treatment. Learn to recognize symptoms and act fast to save lives.",
                source = "WHO, CDC, MSF",
                category = "Infectious Diseases"
            ),
            
            // TYPHOID
            Article(
                title = "Typhoid Fever: Symptoms, Treatment, and Prevention",
                content = """
OVERVIEW

Typhoid fever is a serious bacterial infection caused by Salmonella typhi, spread through contaminated water and food. The WHO estimates 11-20 million cases and 128,000-161,000 deaths annually, mostly in areas with poor sanitation.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SYMPTOMS

Symptoms usually develop 1-3 weeks after infection:

WEEK 1:
• Gradually increasing fever
• Headache
• Weakness and fatigue
• Muscle aches
• Loss of appetite

WEEK 2-3:
• High fever (39-40°C/102-104°F)
• Abdominal pain
• Constipation OR diarrhea
• Rose-colored spots on chest/abdomen
• Enlarged liver and spleen

⚠️ DANGER SIGNS:
• Severe abdominal pain
• Bloody stools
• Confusion
• Persistent high fever

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TREATMENT

1. SEEK MEDICAL CARE
   • Blood test confirms diagnosis
   • Antibiotics are essential
   • Complete the FULL course (usually 7-14 days)

2. HOME CARE
   • Rest in bed
   • Drink plenty of clean fluids
   • Eat small, frequent meals
   • Take paracetamol for fever

3. PREVENT SPREAD
   • Wash hands frequently
   • Don't prepare food for others while sick
   • Use separate toilet if possible
   • Continue hygiene 2 weeks after recovery

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREVENTION

1. SAFE WATER
   • Boil or treat all drinking water
   • Avoid ice from unknown sources
   • Use safe water for brushing teeth

2. FOOD SAFETY
   • Eat thoroughly cooked, hot foods
   • Avoid raw vegetables and salads
   • Peel fruits yourself
   • Avoid street food during outbreaks

3. HAND HYGIENE
   • Wash hands with soap before eating
   • Wash hands after using toilet
   • Use hand sanitizer when soap unavailable

4. VACCINATION
   • Vaccines available for travelers and endemic areas
   • Ask your health provider about vaccination
   • Vaccine doesn't replace safe food/water practices

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

🥗 NUTRITION
• Eat a balanced diet to maintain immunity
• Include fruits and vegetables (washed/cooked)
• Protein-rich foods help recovery
• Avoid raw or undercooked foods

💧 HYDRATION
• Drink at least 2 liters of safe water daily
• Herbal teas can help (boiled water)
• Oral rehydration if fever is high
• Avoid unpasteurized drinks

🏠 HOME ENVIRONMENT
• Maintain clean cooking areas
• Store food properly
• Keep toilet areas clean
• Dispose of waste safely

💪 BUILDING IMMUNITY
• Get adequate sleep (7-8 hours)
• Exercise regularly when healthy
• Manage stress effectively
• Complete vaccination schedules

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/immunization/diseases/typhoid

📚 Centers for Disease Control and Prevention
   www.cdc.gov/typhoid-fever

📚 Sabin Vaccine Institute
   www.sabin.org/programs/typhoid

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Typhoid is preventable with safe water and food. If you have prolonged fever, get tested - early treatment prevents complications.
                """.trimIndent(),
                summary = "Complete guide to typhoid fever - recognize symptoms early and learn prevention strategies for safe water and food.",
                source = "WHO, CDC, Sabin Institute",
                category = "Infectious Diseases"
            ),
            
            // HIV/AIDS
            Article(
                title = "HIV/AIDS: Prevention, Testing, and Living Healthy",
                content = """
OVERVIEW

HIV (Human Immunodeficiency Virus) attacks the immune system. Without treatment, it can lead to AIDS. According to UNAIDS, approximately 38 million people live with HIV globally. With proper treatment, people with HIV can live long, healthy lives.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HOW HIV IS TRANSMITTED

HIV is spread through:
• Unprotected sexual contact
• Sharing needles or syringes
• Mother to child (pregnancy, birth, breastfeeding)
• Blood transfusions (rare with modern screening)

HIV is NOT spread through:
• Casual contact (hugging, shaking hands)
• Sharing food, drinks, or utensils
• Mosquito bites
• Toilet seats
• Air, water, or saliva

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SYMPTOMS

EARLY INFECTION (2-4 weeks):
• Flu-like symptoms
• Fever, fatigue
• Swollen lymph nodes
• Sore throat, rash

CHRONIC HIV (may have no symptoms for years)

AIDS (without treatment):
• Rapid weight loss
• Recurring fever
• Extreme fatigue
• Prolonged swelling of lymph nodes
• Frequent infections
• Skin rashes or sores

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TESTING

GET TESTED IF YOU:
• Have had unprotected sex
• Have multiple sexual partners
• Share injection equipment
• Are pregnant (all pregnant women should test)
• Want to know your status

Testing is:
• Free at most health facilities
• Confidential
• Quick (results in 15-30 minutes)
• The only way to know your status

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TREATMENT

ANTIRETROVIRAL THERAPY (ART):
• Available free in most African countries
• Take daily as prescribed
• Reduces HIV to undetectable levels
• Prevents transmission to others
• Allows normal, healthy life

U=U: Undetectable = Untransmittable
When HIV is undetectable, it cannot be sexually transmitted.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREVENTION

1. USE CONDOMS
   • Male or female condoms
   • Use correctly every time
   • Store properly (away from heat)

2. GET TESTED REGULARLY
   • Know your status
   • Encourage partners to test
   • Test every 3-6 months if at risk

3. LIMIT PARTNERS
   • Fewer partners = lower risk
   • Mutual monogamy with tested partner

4. PrEP (Pre-Exposure Prophylaxis)
   • Daily pill prevents HIV
   • For HIV-negative people at high risk
   • Ask your health provider

5. PREVENT MOTHER-TO-CHILD TRANSMISSION
   • All pregnant women should test
   • ART during pregnancy prevents transmission
   • Safe delivery practices
   • Follow infant feeding guidelines

6. NEVER SHARE NEEDLES
   • Use clean needles always
   • Access needle exchange programs

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE WITH HIV

🥗 NUTRITION
• Eat balanced, nutritious meals
• Include proteins, fruits, vegetables
• Avoid raw or undercooked foods
• Stay well-hydrated
• Take supplements if recommended

💪 PHYSICAL ACTIVITY
• Regular exercise strengthens immunity
• 30 minutes daily (walking, cycling)
• Start slowly, increase gradually
• Rest when needed

😴 REST & MENTAL HEALTH
• Get 7-8 hours of sleep
• Manage stress (meditation, counseling)
• Join support groups
• Stay connected with loved ones

🏥 MEDICAL CARE
• Take ART exactly as prescribed
• Never skip doses
• Attend all clinic appointments
• Get vaccinations as recommended
• Treat other infections promptly

❌ AVOID
• Smoking and alcohol
• Unprotected sex
• Sharing personal items (razors, toothbrushes)
• Stress and isolation

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

REDUCING STIGMA

HIV does not define a person. People living with HIV:
• Can live normal, productive lives
• Deserve respect and support
• Should not face discrimination
• Need community acceptance

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 UNAIDS
   www.unaids.org

📚 World Health Organization (WHO)
   www.who.int/hiv

📚 Centers for Disease Control and Prevention
   www.cdc.gov/hiv

📚 PEPFAR (U.S. President's Emergency Plan for AIDS Relief)
   www.pepfar.gov

📚 Uganda AIDS Commission
   www.uac.go.ug

📚 Togo National AIDS Program
   pnls.tg

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: HIV is preventable and treatable. Get tested, know your status, and access treatment. With ART, you can live a long, healthy life.
                """.trimIndent(),
                summary = "Comprehensive HIV/AIDS guide: prevention, testing, treatment, and living healthy. Based on UNAIDS and WHO guidelines.",
                source = "WHO, UNAIDS, CDC, PEPFAR",
                category = "Sexual Health"
            ),
            
            // MATERNAL HEALTH
            Article(
                title = "Pregnancy and Safe Motherhood: Essential Guide",
                content = """
OVERVIEW

According to the WHO, approximately 295,000 women die during pregnancy and childbirth each year, mostly from preventable causes. This guide provides essential information for a healthy pregnancy and safe delivery.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PRENATAL CARE

ATTEND ALL ANTENATAL VISITS:
WHO recommends at least 8 contacts during pregnancy:
• First visit: Before 12 weeks
• Regular visits throughout pregnancy
• More frequent in third trimester

AT EACH VISIT:
• Blood pressure check
• Weight monitoring
• Urine test
• Baby's growth and heartbeat
• Nutrition counseling
• Birth planning

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

DANGER SIGNS - SEEK HELP IMMEDIATELY

⚠️ During Pregnancy:
• Vaginal bleeding
• Severe headache or blurred vision
• High fever
• Severe abdominal pain
• Reduced or no baby movement
• Swelling of face and hands
• Convulsions

⚠️ During Labor:
• Labor lasting more than 12 hours
• Heavy bleeding
• Cord or hand coming out first
• Mother unable to push

⚠️ After Delivery:
• Heavy bleeding (soaking >1 pad/hour)
• High fever
• Foul-smelling discharge
• Severe headache
• Convulsions

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

ESSENTIAL CARE DURING PREGNANCY

1. NUTRITION
   • Eat diverse, nutritious foods
   • Extra meal daily (eating for two)
   • Iron-rich foods (green vegetables, meat, beans)
   • Take iron and folic acid supplements

2. MEDICATIONS
   • Take prescribed supplements
   • Malaria prevention (IPTp) in endemic areas
   • Deworming tablets as prescribed
   • Avoid self-medication

3. VACCINATIONS
   • Tetanus vaccines (protect mother and baby)
   • COVID-19 vaccine (safe during pregnancy)

4. AVOID
   • Alcohol (no safe amount)
   • Smoking and secondhand smoke
   • Heavy lifting
   • Traditional medicines without doctor approval

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

PREPARING FOR BIRTH

BIRTH PLAN - DISCUSS WITH FAMILY:
1. Where will you deliver? (Health facility recommended)
2. How will you get there?
3. Who will accompany you?
4. Do you have money saved for transport/emergency?
5. Who will care for other children?

ITEMS TO PREPARE:
• Clean clothes for mother and baby
• Clean cloths/towels
• Soap
• Sanitary pads
• Baby blanket

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

AFTER DELIVERY (POSTNATAL CARE)

FOR MOTHER:
• Rest as much as possible
• Continue nutritious eating
• Stay hydrated
• Watch for danger signs
• Take iron supplements
• Attend postnatal visits

FOR BABY:
• Immediate skin-to-skin contact
• Breastfeed within 1 hour of birth
• Exclusive breastfeeding for 6 months
• Keep baby warm
• Keep cord clean and dry
• Vaccinations on schedule

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

BREASTFEEDING

BENEFITS:
• Best nutrition for baby
• Protects against infections
• Helps mother recover
• Creates bonding
• Free and always available

TIPS:
• Start within 1 hour of birth
• Breastfeed on demand (8-12 times daily)
• No water, other foods, or drinks for 6 months
• Continue breastfeeding up to 2 years
• Seek help if having difficulties

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

🥗 NUTRITION
• Eat 3 meals + snacks daily
• Include: proteins, vegetables, fruits, grains
• Iron-rich foods prevent anemia
• Calcium for strong bones (milk, small fish with bones)
• Avoid raw meat/fish, unpasteurized products

💧 HYDRATION
• Drink 8-10 glasses of clean water daily
• Avoid alcohol completely
• Limit caffeine (tea, coffee)

💪 PHYSICAL ACTIVITY
• Light exercise is beneficial
• Walking 30 minutes daily
• Avoid heavy lifting
• Rest when tired

😴 REST
• Sleep 8 hours at night
• Nap during the day if possible
• Sleep on your left side in late pregnancy

🧠 MENTAL HEALTH
• Share feelings with trusted people
• Seek help for anxiety or depression
• Join mother support groups
• Prepare emotionally for parenthood

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

FAMILY PLANNING

After delivery, discuss family planning:
• Healthy spacing: wait 2 years between pregnancies
• Many methods available (pills, injections, implants, IUDs)
• Consult health provider about best option
• Breastfeeding alone is NOT reliable contraception

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/health-topics/maternal-health

📚 UNICEF - Maternal and Newborn Health
   www.unicef.org/health/maternal-and-newborn-health

📚 UNFPA - Sexual and Reproductive Health
   www.unfpa.org

📚 Every Mother Counts
   everymothercounts.org

📚 White Ribbon Alliance
   www.whiteribbonalliance.org

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Every pregnancy should be wanted, every birth safe. Attend prenatal care, know the danger signs, and deliver at a health facility.
                """.trimIndent(),
                summary = "Complete pregnancy and safe motherhood guide based on WHO recommendations. Prenatal care, danger signs, delivery, and newborn care.",
                source = "WHO, UNICEF, UNFPA",
                category = "Maternal Health"
            ),
            
            // NUTRITION
            Article(
                title = "Nutrition and Healthy Eating for Strong Immunity",
                content = """
OVERVIEW

Good nutrition is the foundation of health. According to the WHO, undernutrition contributes to 45% of deaths in children under 5. Eating well protects against disease and helps the body fight infections.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

THE BALANCED DIET

Eat from all food groups daily:

1. ENERGY FOODS (Carbohydrates)
   • Maize, millet, sorghum, rice
   • Cassava, yams, potatoes
   • Bread, pasta
   • Provide energy for daily activities

2. BODY-BUILDING FOODS (Proteins)
   • Beans, lentils, peas, groundnuts
   • Fish, chicken, eggs, meat
   • Milk, yogurt, cheese
   • Build and repair muscles and tissues

3. PROTECTIVE FOODS (Vitamins & Minerals)
   • Dark green vegetables (spinach, kale)
   • Orange vegetables (carrots, pumpkin)
   • Fruits (mangoes, oranges, bananas, papaya)
   • Protect against disease

4. HEALTHY FATS
   • Groundnut oil, palm oil, sunflower oil
   • Avocado
   • Seeds and nuts
   • Essential for brain and body function

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

EATING FOR IMMUNITY

Foods that strengthen your immune system:

🍊 VITAMIN A (fights infections)
• Orange and yellow fruits/vegetables
• Dark green leaves
• Eggs, liver, fish

🍋 VITAMIN C (boosts immunity)
• Citrus fruits (oranges, lemons)
• Tomatoes, peppers
• Guava, mango, papaya

🥩 IRON (prevents anemia)
• Red meat, liver
• Beans, lentils
• Dark green vegetables
• Eat with vitamin C for better absorption

🐟 ZINC (supports immune function)
• Meat, fish, shellfish
• Beans, nuts, seeds
• Whole grains

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

INFANT AND CHILD NUTRITION

0-6 MONTHS:
• Exclusive breastfeeding only
• No water, juices, or other foods
• Breast milk is complete nutrition

6-12 MONTHS:
• Continue breastfeeding
• Start soft, mashed foods
• Feed 2-3 times daily + snacks
• Include animal foods, vegetables, fruits

1-2 YEARS:
• Continue breastfeeding
• Family foods, cut small
• Feed 3-4 times daily + snacks
• Variety is important

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

FOOD SAFETY

1. CLEAN
   • Wash hands before cooking/eating
   • Wash fruits and vegetables
   • Keep cooking area clean

2. SEPARATE
   • Keep raw meat away from other foods
   • Use separate cutting boards
   • Store raw meat at bottom of container

3. COOK
   • Cook food thoroughly
   • Boil, fry, or roast until hot throughout
   • Reheat leftovers properly

4. CHILL
   • Eat food soon after cooking
   • Cover and refrigerate leftovers
   • Don't eat spoiled food

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

🥗 EATING HABITS
• Eat 3 meals daily, don't skip breakfast
• Include variety of foods
• Eat fruits and vegetables daily
• Choose whole grains over refined
• Limit sugar, salt, and processed foods

💧 HYDRATION
• Drink 6-8 glasses of clean water daily
• Limit sugary drinks and sodas
• Avoid excessive alcohol

💪 PHYSICAL ACTIVITY
• Exercise helps maintain healthy weight
• 30 minutes daily
• Walking, farming, housework all count

📊 WEIGHT MANAGEMENT
• Maintain healthy weight
• Too thin = weakened immunity
• Overweight = risk of diabetes, heart disease
• Eat appropriate portions

🍽️ MEAL PLANNING
• Plan meals in advance
• Use local, seasonal foods
• Grow vegetables if possible
• Budget wisely for nutritious foods

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

AFFORDABLE NUTRITION TIPS

• Beans and lentils are cheap protein sources
• Buy seasonal fruits and vegetables
• Grow a small vegetable garden
• Dry or preserve foods when abundant
• Combine grains with legumes for complete protein

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/nutrition

📚 FAO - Food and Agriculture Organization
   www.fao.org/nutrition

📚 UNICEF Nutrition
   www.unicef.org/nutrition

📚 World Food Programme
   www.wfp.org

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Good nutrition is medicine. Eat a variety of foods, practice food safety, and feed children well for a healthy community.
                """.trimIndent(),
                summary = "WHO-based nutrition guide for strong immunity. Learn what to eat, food safety, and affordable healthy eating tips for all ages.",
                source = "WHO, FAO, UNICEF, WFP",
                category = "Nutrition"
            ),
            
            // CLEAN WATER AND HYGIENE
            Article(
                title = "Clean Water and Hygiene: Preventing Disease",
                content = """
OVERVIEW

According to the WHO, 2 billion people lack access to safe drinking water, and poor hygiene causes many preventable diseases. Simple practices like handwashing and water treatment can prevent diarrhea, cholera, typhoid, and other illnesses.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SAFE DRINKING WATER

WATER TREATMENT METHODS:

1. BOILING (Most Effective)
   • Bring water to a rolling boil
   • Boil for at least 1 minute
   • Let it cool naturally
   • Store in clean, covered container

2. CHLORINE TREATMENT
   • Use water purification tablets
   • Or add 2 drops of bleach per liter
   • Wait 30 minutes before drinking
   • Water should smell slightly of chlorine

3. FILTRATION
   • Use ceramic or cloth filters
   • Clean filters regularly
   • Replace as recommended

4. SOLAR DISINFECTION (SODIS)
   • Fill clear plastic bottles with water
   • Place in direct sunlight for 6+ hours
   • Works best in hot, sunny weather

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

WATER STORAGE

• Use clean containers with covers
• Clean containers regularly with soap
• Don't put hands into water storage
• Use a clean cup or ladle
• Keep containers off the ground

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HANDWASHING

WHEN TO WASH HANDS:
• Before eating or preparing food
• After using the toilet
• After changing diapers
• After touching animals
• After coughing or sneezing
• When hands are visibly dirty
• After handling garbage

HOW TO WASH HANDS PROPERLY:
1. Wet hands with clean water
2. Apply soap (any soap works)
3. Rub hands together for 20 seconds
4. Clean between fingers and under nails
5. Rinse with clean water
6. Dry with clean cloth or air dry

NO SOAP? USE:
• Ash
• Sand
• Lemon juice

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SANITATION

SAFE TOILET USE:
• Use latrines or toilets
• Build latrines away from water sources
• Cover pit after use
• Wash hands after every use

DISPOSE OF WASTE SAFELY:
• Don't defecate in the open
• Children's feces are dangerous too
• Clean up animal waste
• Dispose of garbage properly

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HOME HYGIENE

KITCHEN:
• Keep cooking area clean
• Wash dishes with soap and clean water
• Store food covered
• Clean surfaces daily

GENERAL:
• Sweep and mop floors regularly
• Open windows for ventilation
• Dispose of garbage daily
• Control pests (flies, rats)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE RECOMMENDATIONS

💧 WATER HABITS
• Always treat water before drinking
• Carry treated water when traveling
• Teach children about safe water
• Check water sources regularly

🧼 HYGIENE ROUTINE
• Wash hands multiple times daily
• Bathe regularly
• Keep fingernails short and clean
• Wear clean clothes

🏠 HOME ENVIRONMENT
• Maintain clean living spaces
• Fix leaking pipes and taps
• Ensure good drainage
• Keep toilet/latrine clean

👥 COMMUNITY ACTION
• Protect water sources
• Report broken pipes
• Participate in clean-up campaigns
• Share knowledge with neighbors

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TEACHING CHILDREN

Make hygiene fun for children:
• Sing songs while washing hands
• Make it a game
• Lead by example
• Praise good hygiene habits
• Explain why hygiene matters

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/water_sanitation_health

📚 UNICEF WASH
   www.unicef.org/wash

📚 WaterAid
   www.wateraid.org

📚 Centers for Disease Control - Safe Water
   www.cdc.gov/healthywater

📚 Global Handwashing Partnership
   www.globalhandwashing.org

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Clean water and proper hygiene prevent most diarrheal diseases. These simple practices save lives - make them daily habits.
                """.trimIndent(),
                summary = "Essential guide to clean water treatment and hygiene practices. Learn handwashing, water safety, and sanitation to prevent disease.",
                source = "WHO, UNICEF, CDC, WaterAid",
                category = "Hygiene & Sanitation"
            ),
            
            // FIRST AID
            Article(
                title = "First Aid Basics: Essential Emergency Skills",
                content = """
OVERVIEW

Basic first aid knowledge can save lives. This guide covers essential emergency responses that anyone can learn and apply until medical help arrives.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

GENERAL FIRST AID PRINCIPLES

1. STAY CALM - Think clearly
2. ENSURE SAFETY - Check for dangers
3. CALL FOR HELP - Get medical assistance
4. PROVIDE CARE - Help the injured person

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

BLEEDING

FOR MINOR CUTS:
• Wash hands first
• Clean wound with clean water
• Apply pressure with clean cloth
• Cover with clean bandage

FOR SEVERE BLEEDING:
• Apply firm pressure with clean cloth
• Keep pressing - don't remove cloth
• If blood soaks through, add more cloth
• Raise injured limb above heart level
• Get medical help immediately

⚠️ Don't remove objects stuck in wounds - stabilize them

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

BURNS

FOR MINOR BURNS:
• Cool with clean, cool running water for 10-20 minutes
• Remove jewelry/tight items
• Cover with clean, non-stick bandage
• Take pain relief if needed

FOR SEVERE BURNS:
• Cool the burn (not with ice)
• Don't break blisters
• Don't apply creams, oil, or butter
• Cover loosely with clean cloth
• Seek immediate medical care

⚠️ For chemical burns, remove contaminated clothing and flush with water

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

CHOKING

IF PERSON CAN COUGH:
• Encourage them to keep coughing
• Stay with them

IF PERSON CANNOT BREATHE/COUGH:
1. Stand behind them
2. Place fist above belly button
3. Grasp fist with other hand
4. Give quick upward thrusts
5. Repeat until object comes out

FOR INFANTS:
1. Lay baby face-down on your forearm
2. Support head
3. Give 5 back blows between shoulder blades
4. Turn over, give 5 chest thrusts
5. Repeat until object comes out

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

UNCONSCIOUSNESS

IF PERSON IS UNCONSCIOUS BUT BREATHING:
1. Check for response (tap, call name)
2. Call for help
3. Place in recovery position (on side)
4. Monitor breathing until help arrives

RECOVERY POSITION:
• Kneel beside person
• Place nearest arm at right angle
• Bring far arm across chest
• Bend far knee
• Roll toward you onto their side
• Tilt head back slightly

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SEIZURES/CONVULSIONS

DURING SEIZURE:
• Stay calm
• Clear area of dangerous objects
• Protect head with soft material
• Time the seizure
• Don't put anything in mouth
• Don't restrain the person

AFTER SEIZURE:
• Place in recovery position
• Stay with them until fully alert
• Seek medical help if:
  - First seizure
  - Seizure lasts >5 minutes
  - Person doesn't wake up
  - Person is pregnant
  - Person is injured

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SNAKE BITES

DO:
• Keep person calm and still
• Remove jewelry near bite
• Keep bitten area below heart level
• Get to hospital immediately
• Remember snake's appearance if safe

DON'T:
• Cut the wound
• Suck out venom
• Apply ice
• Apply tourniquet
• Give alcohol

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

FEVER

FOR HIGH FEVER:
• Remove excess clothing
• Give fluids (water, ORS)
• Sponge with lukewarm water
• Give paracetamol (correct dose for age)
• Seek medical help if fever doesn't improve

⚠️ For children: Seek help immediately if fever with stiff neck, rash, or convulsions

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE FOR PREVENTION

🏠 HOME SAFETY
• Store medicines out of children's reach
• Keep matches/chemicals safely
• Cover wells and water containers
• Clear paths to prevent falls

🚗 TRAVEL SAFETY
• Use seatbelts
• Don't drink and drive
• Supervise children near roads
• Carry first aid kit when traveling

💪 GENERAL HEALTH
• Stay fit to respond in emergencies
• Learn first aid skills
• Keep emergency numbers accessible
• Know location of nearest health facility

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

FIRST AID KIT ESSENTIALS

Keep these items at home:
• Clean bandages and gauze
• Adhesive tape
• Scissors
• Antiseptic solution
• Paracetamol
• ORS packets
• Thermometer
• Clean gloves
• Emergency contact numbers

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/emergencies

📚 Red Cross / Red Crescent
   www.ifrc.org/first-aid

📚 St John Ambulance
   www.sja.org.uk/first-aid-advice

📚 American Heart Association
   www.heart.org/en/cpr

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: First aid saves lives. Stay calm, get help, and provide basic care. Your quick action can make the difference between life and death.
                """.trimIndent(),
                summary = "Essential first aid guide: bleeding, burns, choking, seizures, and more. Learn life-saving skills everyone should know.",
                source = "WHO, Red Cross, St John Ambulance",
                category = "Emergency Care"
            ),
            
            // MENTAL HEALTH
            Article(
                title = "Mental Health and Emotional Wellbeing",
                content = """
OVERVIEW

Mental health is just as important as physical health. According to the WHO, 1 in 4 people will experience a mental health condition in their lifetime. Good mental health helps us cope with stress, work productively, and contribute to our communities.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

UNDERSTANDING MENTAL HEALTH

Mental health includes:
• Emotional wellbeing
• Ability to cope with stress
• Healthy relationships
• Productive work and activities
• Making meaningful contributions

Mental health problems are:
• Common and treatable
• Not a sign of weakness
• Not caused by evil spirits
• Medical conditions like any other

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

COMMON MENTAL HEALTH CONDITIONS

DEPRESSION
Signs:
• Persistent sadness
• Loss of interest in activities
• Sleep problems
• Fatigue
• Difficulty concentrating
• Feeling worthless
• Thoughts of death or suicide

ANXIETY
Signs:
• Excessive worry
• Restlessness
• Racing heart
• Difficulty sleeping
• Avoiding situations
• Physical symptoms (headaches, stomach problems)

POST-TRAUMATIC STRESS
After experiencing trauma:
• Flashbacks or nightmares
• Avoiding reminders of trauma
• Feeling on edge
• Emotional numbness

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

WHEN TO SEEK HELP

Seek professional help if you experience:
• Persistent sadness or worry (more than 2 weeks)
• Difficulty doing daily activities
• Thoughts of harming yourself or others
• Substance use to cope
• Significant changes in sleep or appetite
• Withdrawal from friends and family

⚠️ CRISIS: If you or someone has thoughts of suicide, seek immediate help. This is a medical emergency.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

HEALTHY LIFESTYLE FOR MENTAL WELLNESS

🏃 PHYSICAL ACTIVITY
• Exercise releases mood-boosting chemicals
• 30 minutes daily
• Walking, dancing, sports, gardening
• Start small, build gradually

😴 QUALITY SLEEP
• 7-8 hours per night
• Regular sleep schedule
• Limit screen time before bed
• Create calm sleep environment

🥗 NUTRITION
• Balanced diet supports brain health
• Regular meals
• Limit alcohol and caffeine
• Stay hydrated

👥 SOCIAL CONNECTION
• Spend time with loved ones
• Join community activities
• Share feelings with trusted people
• Help others

🧘 STRESS MANAGEMENT
• Deep breathing exercises
• Prayer or meditation
• Time in nature
• Enjoyable hobbies

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

COPING STRATEGIES

WHEN FEELING STRESSED:
• Take slow, deep breaths
• Go for a walk
• Talk to someone you trust
• Focus on what you can control
• Take one step at a time

WHEN FEELING SAD:
• Acknowledge your feelings
• Be kind to yourself
• Stay connected with others
• Maintain daily routine
• Seek professional help if needed

WHEN FEELING ANXIOUS:
• Practice deep breathing
• Ground yourself (5 things you see, 4 you hear, 3 you feel)
• Challenge negative thoughts
• Take breaks from worrying
• Focus on the present moment

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

SUPPORTING OTHERS

HOW TO HELP:
• Listen without judgment
• Show you care
• Encourage professional help
• Stay in regular contact
• Learn about their condition
• Take care of yourself too

WHAT TO SAY:
• "I'm here for you"
• "How can I help?"
• "You're not alone"
• "It's okay to not be okay"
• "Would you like me to help you find support?"

WHAT NOT TO SAY:
• "Just cheer up"
• "Others have it worse"
• "It's all in your head"
• "You don't look sick"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

REDUCING STIGMA

Mental health conditions:
• Are real medical conditions
• Can affect anyone
• Are treatable
• Are not shameful
• Don't define a person

How to reduce stigma:
• Educate yourself and others
• Use respectful language
• Share stories of recovery
• Support those affected
• Challenge discrimination

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

TREATMENT OPTIONS

Treatment is available and effective:
• Talking therapy/counseling
• Medication (when needed)
• Support groups
• Community support
• Traditional healing (alongside medical care)

Treatment can be accessed at:
• Health centers and hospitals
• Community mental health services
• Schools and workplaces
• Religious organizations
• Telephone helplines

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

OFFICIAL SOURCES

📚 World Health Organization (WHO)
   www.who.int/mental_health

📚 Mental Health Foundation
   www.mentalhealth.org

📚 Mind (Mental Health Charity)
   www.mind.org.uk

📚 UNHCR Mental Health
   www.unhcr.org/mental-health

📚 BasicNeeds
   www.basicneeds.org

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Remember: Mental health matters. It's okay to struggle, and it's brave to seek help. With support, people recover and live fulfilling lives.
                """.trimIndent(),
                summary = "Comprehensive mental health guide: understanding emotions, coping strategies, when to seek help, and supporting others.",
                source = "WHO, Mental Health Foundation, Mind",
                category = "Mental Health"
            )
        )
    }
}
