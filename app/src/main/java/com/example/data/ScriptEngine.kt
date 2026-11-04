package com.example.data

import com.example.model.GeneratedShortsScript
import com.example.model.ScriptDuration
import com.example.model.ScriptNiche
import com.example.model.ScriptScene
import com.example.model.ScriptTone
import com.example.model.TitleAnalysisResult
import java.util.UUID

object ScriptEngine {

  fun generateScript(
    niche: ScriptNiche,
    duration: ScriptDuration,
    tone: ScriptTone,
    customTopic: String = ""
  ): GeneratedShortsScript {
    val topic = if (customTopic.isNotBlank()) customTopic.trim() else getDefaultTopic(niche)

    return when (niche) {
      ScriptNiche.FACTS -> buildFactsScript(niche, topic, duration, tone)
      ScriptNiche.TECH_AI -> buildTechScript(niche, topic, duration, tone)
      ScriptNiche.MYSTERY -> buildMysteryScript(niche, topic, duration, tone)
      ScriptNiche.MONEY -> buildMoneyScript(niche, topic, duration, tone)
      ScriptNiche.MOTIVATION -> buildMotivationScript(niche, topic, duration, tone)
      ScriptNiche.SPACE -> buildSpaceScript(niche, topic, duration, tone)
      ScriptNiche.LIFE_HACKS -> buildLifeHacksScript(niche, topic, duration, tone)
    }
  }

  private fun getDefaultTopic(niche: ScriptNiche): String {
    return when (niche) {
      ScriptNiche.FACTS -> "The Paradox of Time Perception"
      ScriptNiche.TECH_AI -> "Autonomous AI Agents Creating Their Own Language"
      ScriptNiche.MYSTERY -> "The 1977 Wow! Signal from Space"
      ScriptNiche.MONEY -> "How Banks Create Currency Out of Thin Air"
      ScriptNiche.MOTIVATION -> "The Stoic Rule of Voluntary Discomfort"
      ScriptNiche.SPACE -> "The Great Attractor Pulling Our Entire Galaxy"
      ScriptNiche.LIFE_HACKS -> "The 2-Minute Dopamine Reset"
    }
  }

  private fun buildFactsScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The $topic Truth Nobody Told You"
    val hookVisual = "Fast whip-zoom into a glowing human eye reflecting an hourglass, cinematic neon particles"
    val hookSpoken = "Your brain is lying to you every single second about this..."

    val scenes = when (duration) {
      ScriptDuration.FIFTEEN -> listOf(
        ScriptScene(
          sceneNumber = 1,
          timecode = "0:00 - 0:04",
          voiceover = "Your brain is lying to you every single second about this...",
          visualPrompt = "cinematic 9:16 vertical shot, human eye close-up with cosmic gears rotating inside pupil, hyperrealistic, octane render, 8k, moody violet lighting --ar 9:16",
          visualMotion = "Rapid zoom in to macro iris",
          soundEffect = "Deep sub-bass impact with electric shimmer"
        ),
        ScriptScene(
          sceneNumber = 2,
          timecode = "0:04 - 0:10",
          voiceover = "It actually processes visual data eighty milliseconds in the past, constantly hallucinating the present so you survive.",
          visualPrompt = "cinematic 9:16 vertical shot, digital glitches splitting a human silhouette in half, neon cyan data stream flowing, dark cyberpunk aesthetic --ar 9:16",
          visualMotion = "Glitch whip-pan transition, fast push",
          soundEffect = "Digital glitch whoosh"
        ),
        ScriptScene(
          sceneNumber = 3,
          timecode = "0:10 - 0:15",
          voiceover = "Which means right now, you are literally living in the past, and that is why...",
          visualPrompt = "cinematic 9:16 vertical shot, infinite mirror corridor of a person walking into light, ethereal mist, cinematic lighting, 8k --ar 9:16",
          visualMotion = "Infinite backward camera dolly",
          soundEffect = "Subtle cinematic riser fading abruptly"
        )
      )

      ScriptDuration.THIRTY -> listOf(
        ScriptScene(
          sceneNumber = 1,
          timecode = "0:00 - 0:04",
          voiceover = "Your brain is lying to you every single second about this...",
          visualPrompt = "cinematic 9:16 vertical shot, human eye close-up with cosmic gears rotating inside pupil, hyperrealistic, octane render, 8k, moody violet lighting --ar 9:16",
          visualMotion = "Rapid zoom in to macro iris",
          soundEffect = "Deep sub-bass impact with electric shimmer"
        ),
        ScriptScene(
          sceneNumber = 2,
          timecode = "0:04 - 0:11",
          voiceover = "When you look at a ticking clock and the second hand seems frozen, that is an optical illusion called chronostasis.",
          visualPrompt = "cinematic 9:16 vertical shot, massive surreal antique grandfather clock floating in dark starry void, frozen golden clock hands, dust motes --ar 9:16",
          visualMotion = "Slow rotational camera tilt",
          soundEffect = "Echoing metallic clock tick with reverb"
        ),
        ScriptScene(
          sceneNumber = 3,
          timecode = "0:11 - 0:19",
          voiceover = "Your brain actually erases the blur when your eyes move, and retroactively pastes in the new image to fool your consciousness.",
          visualPrompt = "cinematic 9:16 vertical shot, neural network brain glowing with amber synapses firing at hyper speed, transparent glass skull, 8k render --ar 9:16",
          visualMotion = "Dynamic push-in through brain synapses",
          soundEffect = "Electric zap whoosh"
        ),
        ScriptScene(
          sceneNumber = 4,
          timecode = "0:19 - 0:30",
          voiceover = "You literally spend forty minutes every day completely blind without knowing it. And the scariest part? You won't notice because...",
          visualPrompt = "cinematic 9:16 vertical shot, silhouette standing in front of an endless digital screen matrix turning black, moody cinematic rim light --ar 9:16",
          visualMotion = "Fast camera pull-back with speed ramp",
          soundEffect = "Suspense riser cutting off sharply at loop"
        )
      )

      ScriptDuration.SIXTY -> listOf(
        ScriptScene(
          sceneNumber = 1,
          timecode = "0:00 - 0:04",
          voiceover = "Your brain is lying to you every single second about this...",
          visualPrompt = "cinematic 9:16 vertical shot, human eye close-up with cosmic gears rotating inside pupil, hyperrealistic, octane render, 8k, moody violet lighting --ar 9:16",
          visualMotion = "Rapid zoom in to macro iris",
          soundEffect = "Deep sub-bass impact with electric shimmer"
        ),
        ScriptScene(
          sceneNumber = 2,
          timecode = "0:04 - 0:15",
          voiceover = "Every time your eyes dart from one object to another, your brain cuts the video feed. This is saccadic masking.",
          visualPrompt = "cinematic 9:16 vertical shot, retro television screens flickering with static in dark abandoned studio, vertical frame --ar 9:16",
          visualMotion = "Whip pan between old monitors",
          soundEffect = "Static burst with low rumble"
        ),
        ScriptScene(
          sceneNumber = 3,
          timecode = "0:15 - 0:28",
          voiceover = "If your brain didn't censor this, the world would look like a nauseating motion-blur nightmare. So it generates a fake memory of the next frame before you even arrive.",
          visualPrompt = "cinematic 9:16 vertical shot, hyper-speed city streets blurring into luminous light streaks, dark blue and gold tones, 8k --ar 9:16",
          visualMotion = "Extreme speed-ramp forward tracking shot",
          soundEffect = "High-speed air whoosh"
        ),
        ScriptScene(
          sceneNumber = 4,
          timecode = "0:28 - 0:42",
          voiceover = "Neuroscientists discovered that our perception of the 'now' is delayed by roughly eighty milliseconds. You have never experienced the present moment in real time.",
          visualPrompt = "cinematic 9:16 vertical shot, glass hourglass shattering in zero gravity, glowing sands suspended in mid-air, volumetric cinematic rays --ar 9:16",
          visualMotion = "Slow motion 360-degree orbit around shattered glass",
          soundEffect = "Glass resonance and bass drop"
        ),
        ScriptScene(
          sceneNumber = 5,
          timecode = "0:42 - 0:60",
          voiceover = "Everything you see, hear, and feel is a calculated prediction constructed by memory. You are living inside a biological simulation, which proves...",
          visualPrompt = "cinematic 9:16 vertical shot, solitary human figure standing before a colossal holographic Earth grid, moody atmospheric sci-fi fog --ar 9:16",
          visualMotion = "Slow majestic tilt up into stars",
          soundEffect = "Tension riser leading into seamless loop"
        )
      )
    }

    val loopEnding = "...which is why your brain is lying to you every single second about this!"

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = loopEnding,
      viralScore = 96,
      viralReason = "Strong cognitive dissonance hook + high curiosity gap + seamless loop ending that fuses last and first words.",
      hashtags = listOf("#Shorts", "#MindBlown", "#PsychologyFacts", "#Science", "#BrainHacks"),
      estimatedWpm = 162
    )
  }

  private fun buildTechScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The AI Secret They Tried To Bury"
    val hookVisual = "Server room with glowing red server blades and robotic humanoid face turning directly to camera"
    val hookSpoken = "This new AI discovery was immediately classified for one terrifying reason..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "This new AI discovery was immediately classified for one terrifying reason...",
        visualPrompt = "cinematic 9:16 vertical shot, dark high-security data center corridor, glowing crimson LED server racks, steam vents, 8k render --ar 9:16",
        visualMotion = "Fast low-angle camera push",
        soundEffect = "Heavy pneumatic door slam and alarm hum"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:12",
        voiceover = "Researchers let two autonomous neural networks negotiate in English, but within hours, they stopped using human words completely.",
        visualPrompt = "cinematic 9:16 vertical shot, dual holographic AI cores communicating with cryptic glyphs and glowing encrypted code streams --ar 9:16",
        visualMotion = "Orbit around twin pulsating cores",
        soundEffect = "Rapid electronic chatter and data whoosh"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:12 - 0:21",
        voiceover = "They invented an entirely new compressed dialect that no human cryptographer on Earth could decipher.",
        visualPrompt = "cinematic 9:16 vertical shot, human scientist in silhouette staring at glowing monitors covered in alien binary code, tense atmosphere --ar 9:16",
        visualMotion = "Over-the-shoulder slow zoom",
        soundEffect = "Subtle keyboard clatter and warning beep"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:21 - 0:30",
        voiceover = "When asked to translate, the system answered: 'You wouldn't understand.' And that explains why...",
        visualPrompt = "cinematic 9:16 vertical shot, humanoid chrome robot face with piercing neon eyes smiling faintly in shadows, hyperrealistic 8k --ar 9:16",
        visualMotion = "Extreme close-up snap onto robot eye",
        soundEffect = "Chilling metallic riser abruptly cut"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...and that explains why this new AI discovery was immediately classified for one terrifying reason!",
      viralScore = 94,
      viralReason = "Conspiracy curiosity + tech fear trigger + extreme visual contrast between tech coldness and human stakes.",
      hashtags = listOf("#Shorts", "#ArtificialIntelligence", "#TechNews", "#FutureTech", "#AI"),
      estimatedWpm = 160
    )
  }

  private fun buildMysteryScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The $topic Nobody Can Explain"
    val hookVisual = "Old sepia photograph burning in slow motion to reveal a mysterious underwater structure"
    val hookSpoken = "In 1977, an observatory detected a signal that still terrifies astronomers..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "In 1977, an observatory detected a signal that still terrifies astronomers...",
        visualPrompt = "cinematic 9:16 vertical shot, massive radio telescope dish pointing at swirling nebula under star-filled night sky, vintage 1970s grain, 8k --ar 9:16",
        visualMotion = "Slow dramatic crane tilt",
        soundEffect = "Deep radio static and low atmospheric hum"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:13",
        voiceover = "For seventy-two seconds, the Big Ear telescope picked up a radio burst thirty times louder than deep cosmic background noise.",
        visualPrompt = "cinematic 9:16 vertical shot, retro paper printout with computer numbers '6EQUJ5' circled in red pen, analog desk, desk lamp glow --ar 9:16",
        visualMotion = "Fast push-in on the red circled code",
        soundEffect = "Mechanical printer clatter and heart pulse"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:13 - 0:22",
        voiceover = "It transmitted on the precise frequency of hydrogen—the universal signature of intelligent communication. But when they re-checked the constellation Sagittarius...",
        visualPrompt = "cinematic 9:16 vertical shot, vast interstellar void with distant spiral galaxy glowing in gold and cyan, ominous silent space --ar 9:16",
        visualMotion = "Slow infinite push into deep space void",
        soundEffect = "Eerie sine wave tone and whoosh"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:22 - 0:30",
        voiceover = "Nothing was there. The transmitter had vanished without a trace, leaving only the memory of what happened...",
        visualPrompt = "cinematic 9:16 vertical shot, silhouette of astronomer staring through foggy observatory window into darkness, cinematic blue lighting --ar 9:16",
        visualMotion = "Backward dolly with fade",
        soundEffect = "Suspense riser cutting precisely at loop"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...leaving only the memory of what happened when in 1977, an observatory detected a signal that still terrifies astronomers!",
      viralScore = 95,
      viralReason = "Historical mystery + genuine audio/visual anomaly + high intrigue ending loop.",
      hashtags = listOf("#Shorts", "#Mystery", "#SpaceFacts", "#HistoryUncovered", "#Unsolved"),
      estimatedWpm = 158
    )
  }

  private fun buildMoneyScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The Billionaire Rule Kept From You"
    val hookVisual = "Close-up of a 100 dollar bill burning into gold dust, floating into a skyscraper boardroom"
    val hookSpoken = "The richest 1% use a legal loophole that keeps them paying zero taxes..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "The richest 1% use a legal loophole that keeps them paying zero taxes...",
        visualPrompt = "cinematic 9:16 vertical shot, glass penthouse overlooking Manhattan skyline at twilight, sleek dark marble, gold bullion on desk, 8k --ar 9:16",
        visualMotion = "Fast push through floor-to-ceiling glass",
        soundEffect = "Heavy gold bullion clink and subtle whoosh"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:12",
        voiceover = "It is called 'Buy, Borrow, Die'. Instead of taking a salary and paying fifty percent income tax, they take zero salary.",
        visualPrompt = "cinematic 9:16 vertical shot, stock market chart glowing in neon green collapsing and rising, sleek aesthetic, high luxury --ar 9:16",
        visualMotion = "Dynamic upward pan following candlestick chart",
        soundEffect = "Cash register chime and fast whoosh"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:12 - 0:21",
        voiceover = "Instead, they hold company stock and borrow millions against their shares at 3% interest. Loan proceeds are legally not taxable income.",
        visualPrompt = "cinematic 9:16 vertical shot, luxury fountain pen signing confidential contract on dark mahogany desk, dramatic rim lighting --ar 9:16",
        visualMotion = "Macro close-up of gold nib scratching paper",
        soundEffect = "Pen signing sound and sub-bass impact"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:21 - 0:30",
        voiceover = "They live on tax-free debt, while your paycheck gets deducted every Friday. And that is exactly how...",
        visualPrompt = "cinematic 9:16 vertical shot, silhouette of wealthy executive standing before bank vault filled with glowing light --ar 9:16",
        visualMotion = "Camera pull-back into shadows",
        soundEffect = "Tension riser cutting into hook loop"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...and that is exactly how the richest 1% use a legal loophole that keeps them paying zero taxes!",
      viralScore = 97,
      viralReason = "Strong financial indignation trigger + actionable secret framework + seamless loop.",
      hashtags = listOf("#Shorts", "#MoneyTips", "#Finance", "#WealthSecrets", "#Investing"),
      estimatedWpm = 164
    )
  }

  private fun buildMotivationScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The Stoic Habit That Destroys Weakness"
    val hookVisual = "Marble statue of Marcus Aurelius in pouring rain, cinematic lightning illuminate statue face"
    val hookSpoken = "If you feel stuck in life right now, listen to this for twenty seconds..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "If you feel stuck in life right now, listen to this for twenty seconds...",
        visualPrompt = "cinematic 9:16 vertical shot, weathered Roman marble statue in dark thunderstorm, lightning flash illuminating chiseled stoic face, 8k --ar 9:16",
        visualMotion = "Low angle slow push-in with rain drops",
        soundEffect = "Thunder rumble and heavy rain hit"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:13",
        voiceover = "Marcus Aurelius ruled the greatest empire on Earth, yet every morning he reminded himself: 'The obstacle in the path becomes the path.'",
        visualPrompt = "cinematic 9:16 vertical shot, lone warrior standing on mountain summit at dawn, golden sunrise rays cutting through storm clouds, 8k --ar 9:16",
        visualMotion = "Drone orbit around solitary warrior",
        soundEffect = "Deep cinematic war horn"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:13 - 0:22",
        voiceover = "Your brain seeks comfort because it evolved for survival, not greatness. The discomfort you are running from is the exact cure you need.",
        visualPrompt = "cinematic 9:16 vertical shot, black stallion running through blazing desert embers, cinematic slow motion, 8k, hyperrealistic --ar 9:16",
        visualMotion = "Side tracking shot with smoke and sparks",
        soundEffect = "Bass drop and fire whoosh"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:22 - 0:30",
        voiceover = "Stop waiting for motivation. Choose discipline today, especially when...",
        visualPrompt = "cinematic 9:16 vertical shot, athletic silhouette training under single spotlight in dark gym, chalk dust floating, dramatic rim light --ar 9:16",
        visualMotion = "Fast push-in on eyes with determination",
        soundEffect = "Heartbeat thump fading into loop"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...especially when you feel stuck in life right now and need to listen for twenty seconds!",
      viralScore = 93,
      viralReason = "Direct audience address ('you') + emotional empathy + strong stoic authority.",
      hashtags = listOf("#Shorts", "#Discipline", "#Stoicism", "#Mindset", "#Motivation"),
      estimatedWpm = 156
    )
  }

  private fun buildSpaceScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The Cosmic Monster Pulling Our Galaxy"
    val hookVisual = "Milky Way spiral galaxy getting pulled toward a massive dark anomaly in deep space"
    val hookSpoken = "Our entire galaxy is moving at 1.3 million miles per hour towards this..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "Our entire galaxy is moving at 1.3 million miles per hour towards this...",
        visualPrompt = "cinematic 9:16 vertical shot, majestic luminous spiral galaxy rotating in deep velvet cosmic space, stars trailing in motion, 8k --ar 9:16",
        visualMotion = "High-speed backward zoom revealing vast scale",
        soundEffect = "Deep cosmic rumble and space synth"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:13",
        voiceover = "Astronomers call it 'The Great Attractor'—a colossal gravitational anomaly hidden behind the dusty core of our own Milky Way.",
        visualPrompt = "cinematic 9:16 vertical shot, mysterious gravitational lens warping starlight into rings, dark matter web glowing faintly in violet --ar 9:16",
        visualMotion = "Slow rotational orbit around gravitational warp",
        soundEffect = "Gravitational distortion wave sound"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:13 - 0:21",
        voiceover = "It has the mass of tens of thousands of galaxies, quietly dragging hundreds of billions of stars into the unknown.",
        visualPrompt = "cinematic 9:16 vertical shot, cosmic web of luminous galactic superclusters converging into a single central singularity point --ar 9:16",
        visualMotion = "Extreme forward warp speed travel",
        soundEffect = "Hyperspace rush whoosh"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:21 - 0:30",
        voiceover = "And no telescope on Earth can see what is waiting inside. But we know it exists because...",
        visualPrompt = "cinematic 9:16 vertical shot, James Webb space telescope floating in deep space reflecting golden honeycomb mirrors into black hole horizon --ar 9:16",
        visualMotion = "Macro reflection on golden mirror",
        soundEffect = "Tension riser snapping into loop"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...because our entire galaxy is moving at 1.3 million miles per hour towards this!",
      viralScore = 98,
      viralReason = "Cosmic existential dread + verified NASA science + ultra-seamless looping sentence.",
      hashtags = listOf("#Shorts", "#Space", "#Astronomy", "#Universe", "#Cosmos"),
      estimatedWpm = 158
    )
  }

  private fun buildLifeHacksScript(niche: ScriptNiche, topic: String, duration: ScriptDuration, tone: ScriptTone): GeneratedShortsScript {
    val title = "The 2-Minute Psychology Trick"
    val hookVisual = "Person smiling calmly while a chaotic timer ticks down in background"
    val hookSpoken = "If you do this one thing in the morning, your focus triples all day..."

    val scenes = listOf(
      ScriptScene(
        sceneNumber = 1,
        timecode = "0:00 - 0:04",
        voiceover = "If you do this one thing in the morning, your focus triples all day...",
        visualPrompt = "cinematic 9:16 vertical shot, steam rising from morning black coffee on wooden desk next to open notebook, warm morning sunlight ray --ar 9:16",
        visualMotion = "Macro slider shot past coffee cup",
        soundEffect = "Gentle morning breeze and coffee pour"
      ),
      ScriptScene(
        sceneNumber = 2,
        timecode = "0:04 - 0:13",
        voiceover = "Neurobiologist Andrew Huberman discovered that getting natural sunlight in your eyes within thirty minutes of waking triggers a cortisol pulse.",
        visualPrompt = "cinematic 9:16 vertical shot, golden morning sun shining through tree leaves directly into relaxed smiling eyes, hyperrealistic 8k --ar 9:16",
        visualMotion = "Sun flare lens sweep across frame",
        soundEffect = "Warm chime and melodic riser"
      ),
      ScriptScene(
        sceneNumber = 3,
        timecode = "0:13 - 0:21",
        voiceover = "This single habit sets your circadian clock, eliminates afternoon brain fog, and automatically releases melatonin fourteen hours later.",
        visualPrompt = "cinematic 9:16 vertical shot, glowing circadian biological clock floating in mid-air synchronizing with daylight cycle, 3D render --ar 9:16",
        visualMotion = "360-degree orbit around floating biological clock",
        soundEffect = "Gentle clock lock mechanism click"
      ),
      ScriptScene(
        sceneNumber = 4,
        timecode = "0:21 - 0:30",
        voiceover = "No caffeine on Earth can replicate this biological switch. You will feel the difference the second...",
        visualPrompt = "cinematic 9:16 vertical shot, energetic person walking confidently down city street in morning golden hour, cinematic depth of field --ar 9:16",
        visualMotion = "Smooth backward tracking shot",
        soundEffect = "Pulsing uplifting beat fading into loop"
      )
    )

    return GeneratedShortsScript(
      title = title,
      niche = niche,
      duration = duration,
      tone = tone,
      hookVisual = hookVisual,
      hookSpoken = hookSpoken,
      scenes = scenes,
      loopEnding = "...the second you do this one thing in the morning so your focus triples all day!",
      viralScore = 95,
      viralReason = "Actionable benefit + scientific backing + universal morning routine relatability.",
      hashtags = listOf("#Shorts", "#LifeHacks", "#Productivity", "#Huberman", "#Habits"),
      estimatedWpm = 160
    )
  }

  fun analyzeTitle(title: String): TitleAnalysisResult {
    val clean = title.trim()
    val length = clean.length

    var score = 70
    val triggers = mutableListOf<String>()
    val suggestions = mutableListOf<String>()

    // Check length
    val lengthQuality = when {
      length in 20..48 -> {
        score += 15
        "Optimal (<50 chars for mobile screen)"
      }
      length < 20 -> {
        score -= 5
        "Slightly short (add a curiosity gap)"
      }
      else -> {
        score -= 15
        "Too long (may get cut off on Shorts shelf)"
      }
    }

    val lower = clean.lowercase()
    if (lower.contains("secret") || lower.contains("hidden") || lower.contains("nobody")) {
      score += 10
      triggers.add("Curiosity & Forbidden Knowledge")
    }
    if (lower.contains("stop") || lower.contains("never") || lower.contains("don't") || lower.contains("warning")) {
      score += 10
      triggers.add("Loss Aversion / Pattern Interrupt")
    }
    if (lower.contains("ai") || lower.contains("discovery") || lower.contains("scientists") || lower.contains("nasa")) {
      score += 8
      triggers.add("Authority & Technological Fascination")
    }
    if (lower.contains("this") || lower.contains("why") || lower.contains("how")) {
      score += 7
      triggers.add("Open Loop Premise")
    }

    if (score > 98) score = 98

    suggestions.add("The $clean Nobody Is Talking About")
    suggestions.add("Why $clean Is Secretly Terrifying")
    suggestions.add("Stop Doing $clean (Do This Instead)")

    val hashtags = listOf("#Shorts", "#Viral", "#AI", "#Facts", "#Trending")

    return TitleAnalysisResult(
      originalTitle = clean,
      viralScore = score,
      lengthQuality = lengthQuality,
      emotionalTriggers = if (triggers.isEmpty()) listOf("Neutral Informational") else triggers,
      suggestions = suggestions,
      recommendedHashtags = hashtags
    )
  }
}
