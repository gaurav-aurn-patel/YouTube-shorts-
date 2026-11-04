package com.example.data

import com.example.model.GuidePhase
import com.example.model.HookTemplate

object GuideData {

  val masterPhases: List<GuidePhase> = listOf(
    GuidePhase(
      stepNumber = 1,
      title = "Niche & Viral Idea Discovery",
      timeEstimate = "5 mins",
      tag = "Strategy",
      summary = "Pick high-retention topics that trigger extreme curiosity and repeat watching on the YouTube Shorts shelf.",
      detailedExplanation = """
        The YouTube Shorts algorithm is governed by two master metrics:
        1. Viewed vs. Swiped Away (Target: > 75% Viewed)
        2. Average Percentage Viewed (APV) (Target: > 100% via seamless loops)

        To achieve this with AI, focus on 'High Curiosity Velocity' niches:
        • Unbelievable Paradoxes & Facts: Science, space, deep ocean, bizarre historical anomalies.
        • 'What If' & Alternate Realities: What if the moon collided with Earth? What if dinosaurs never went extinct?
        • Dark Psychology & Life Truths: Stoic principles, human behavior quirks, persuasion secrets.
        • Faceless Tech & AI Tools: Revealing mind-blowing AI websites and automations.

        Use AI prompts to scrape and synthesize trending topics rather than guessing.
      """.trimIndent(),
      keyFormula = "Viral Idea = High Curiosity Gap + Universal Relatability + Visual Contrast",
      aiPrompts = listOf(
        "Give me 10 mind-blowing paradoxical facts about the human brain that sound fake but are 100% scientifically proven.",
        "List 5 historical mysteries from the 1800s that were never solved, formatted as 30-second suspense narratives.",
        "Generate 7 futuristic 'What-If' scenarios involving space exploration with intense visual descriptions."
      ),
      recommendedTools = listOf("ChatGPT", "Claude 3.5 Sonnet", "Google Gemini", "YouTube Search Filter (This Month + Views)"),
      commonMistakes = listOf(
        "Starting with a slow greeting ('Hey guys, welcome back...') - instantly swiped away!",
        "Picking complex topics that require 5 minutes of background context.",
        "Targeting a saturated niche without a unique visual theme."
      ),
      checklistItem = "Selected high-curiosity niche and 3-second hook concept"
    ),

    GuidePhase(
      stepNumber = 2,
      title = "The 3-Second Hook & Script Formula",
      timeEstimate = "10 mins",
      tag = "Scripting",
      summary = "Engineer a hypnotic 4-part script with micro-hooks every 3 seconds and a seamless loop transition.",
      detailedExplanation = """
        Shorts viewers have a 2-second attention span. If you don't shock, intrigue, or present a problem in the first 3 seconds, they swipe.

        The 4-Part Viral Shorts Architecture:
        1. 0:00 - 0:03 The Pattern-Interrupt Hook: Visual motion + Spoken shocker. (e.g., 'Scientists just detected a signal from deep space that shouldn't exist.')
        2. 0:03 - 0:15 The Context & Open Loop: Explain the premise rapidly while withholding the resolution. Never let the viewer guess the outcome early.
        3. 0:15 - 0:45 The Micro-Payoffs: Every 3 to 4 seconds, introduce a new fact, visual shift, or twist. Keep speaking pace at 150-170 WPM.
        4. 0:45 - 0:58 The Climax & Seamless Loop: Cut the ending so the final sentence grammatically fuses with the opening hook words!
      """.trimIndent(),
      keyFormula = "Hook (0-3s) ➔ Open Loop (3-15s) ➔ Micro-Payoffs (15-45s) ➔ Seamless Loop Connection",
      aiPrompts = listOf(
        "Write a 45-second YouTube Shorts script about the Voynich Manuscript. Include spoken timestamps, visual cues for AI video generation, and craft the final sentence to seamlessly loop into the first sentence.",
        "Convert this scientific discovery into a fast-paced 30-second script spoken at 160 WPM. Use a pattern-interrupt hook and sound effect markers."
      ),
      recommendedTools = listOf("ShortsCraft AI Generator", "ChatGPT-4o", "Claude 3.5", "Hemingway App (keep Grade 5-6 readability)"),
      commonMistakes = listOf(
        "Writing paragraphs that read like a blog post instead of spoken punchy dialogue.",
        "Forgetting to design the loop ending.",
        "Using slow, academic vocabulary that slows down the voiceover."
      ),
      checklistItem = "Crafted script with seamless loop and timed under 55 seconds"
    ),

    GuidePhase(
      stepNumber = 3,
      title = "Hyper-Realistic AI Voiceover",
      timeEstimate = "5 mins",
      tag = "Audio",
      summary = "Generate deep, cinematic, or energetic AI voices with natural pacing, micro-breaths, and zero robotic monotone.",
      detailedExplanation = """
        Bad robotic text-to-speech ruins Shorts retention immediately. Today's top Shorts creators use generative voice models like ElevenLabs.

        Best Practices for Shorts Voiceovers:
        • Voice Selection: Choose deep narrative voices for mystery/history (e.g. ElevenLabs 'Adam', 'Marcus', or 'Rachel') or energetic punchy voices for tech/facts.
        • Stability Settings: Keep Stability at 45-55% and Clarity/Similarity at 75-85%. This allows the AI to express subtle pitch variations and dramatic pauses.
        • Speed: Generate at 1.05x or speed up in your editor to 1.1x to eliminate dead air between words.
        • Breath Marks: Add commas and dashes (--) in your text to force dramatic micro-pauses before shocking reveals.
      """.trimIndent(),
      keyFormula = "Pacing = 155-170 WPM + 0.3s micro-pauses before climaxes + Pitch Dynamic",
      aiPrompts = listOf(
        "Voiceover text formatting: In ElevenLabs, type: 'This object was hidden for 2,000 years... [pause] until yesterday.'",
        "Prompt for emotion: Speak with intense suspense, whisper-like urgency, and steady documentary authority."
      ),
      recommendedTools = listOf("ElevenLabs (Leader in AI Voice)", "CapCut AI TTS (Free built-in voices)", "Murf.ai", "Audacity (Noise reduction)"),
      commonMistakes = listOf(
        "Using default monotone robotic voices (viewers immediately detect cheap content).",
        "Leaving long silences between sentences (cut all pauses over 0.2s in post).",
        "Voiceover volume too quiet compared to background music."
      ),
      checklistItem = "Generated clean AI audio file and verified 155+ WPM pacing"
    ),

    GuidePhase(
      stepNumber = 4,
      title = "AI Visuals & B-Roll Generation",
      timeEstimate = "15 mins",
      tag = "Visuals",
      summary = "Generate 9:16 vertical 8K cinematic imagery and dynamic video clips using Midjourney, Flux, and Runway Gen-3.",
      detailedExplanation = """
        A Short requires visual stimulation every 2.5 to 3.5 seconds. For a 30-second Short, you need 8 to 12 distinct scenes or camera motions.

        Two Production Methods:
        1. AI Image to Video (Highest Quality):
           • Step A: Generate stunning vertical 9:16 stills in Midjourney v6 or Flux.1.
           • Step B: Run the still through Runway Gen-3, Luma Dream Machine, or Kling AI to add subtle camera push-ins, smoke, or lighting shifts.
        2. Direct AI Video Generation:
           • Describe prompt with camera movements: 'slow dramatic zoom in', 'whip pan', 'drone flyover', 'macro lens 8k'.

        Aspect Ratio Rule:
        Always append `--ar 9:16` in Midjourney or set 9:16 vertical format in Flux/Runway so you never have to crop landscape shots awkwardly!
      """.trimIndent(),
      keyFormula = "Visual Rule: 1 New Scene or Camera Movement every 2.5 - 3 Seconds",
      aiPrompts = listOf(
        "Midjourney/Flux prompt: cinematic 9:16 vertical shot, ancient glowing temple buried under polar ice, hyperrealistic, octane render, 8k, volumetric lighting, moody dark teal and amber tones --ar 9:16 --v 6.0",
        "Runway/Kling motion prompt: slow dramatic camera dolly push-in towards glowing artifact, particles floating, cinematic anamorphic lens flare"
      ),
      recommendedTools = listOf("Midjourney v6", "Flux.1 Schnell / Dev", "Runway Gen-3 Alpha", "Kling AI", "Luma Dream Machine", "Leonardo.ai"),
      commonMistakes = listOf(
        "Generating 16:9 landscape images and stretching or heavily cropping them.",
        "Using static images with zero motion (always apply Ken Burns zoom or AI motion).",
        "Cluttered backgrounds where the viewer's eye doesn't know where to look."
      ),
      checklistItem = "Rendered 8-12 vertical 9:16 scenes with dynamic motion"
    ),

    GuidePhase(
      stepNumber = 5,
      title = "Dynamic Auto-Captions & Safe Zones",
      timeEstimate = "10 mins",
      tag = "Editing",
      summary = "Add animated word-by-word captions, sound effects on transitions, and position all text inside YouTube safe zones.",
      detailedExplanation = """
        Over 70% of YouTube Shorts viewers watch with sound muted or in public settings. Auto-captions are mandatory.

        The Viral Caption Formula:
        • 1 to 3 words on screen at a time (never display full paragraphs!).
        • Bold, heavy font (The Bold Font, Montserrat Black, Komika Axis).
        • High-contrast colors: Bright Yellow, Lime Green, or Pure White with black drop-shadow / outline.
        • Word-by-word pop or scale animation as the voice speaks.

        The YouTube Shorts Safe Zone:
        YouTube overlays UI elements on top of your video:
        • Top 15%: Channel header & search bar
        • Bottom 20%: Title, channel icon, audio disc, and subscribe button
        • Right side 15%: Like, Dislike, Comment, Share, Remix buttons
        Always place your captions directly in the dead center (35% to 55% height)!
      """.trimIndent(),
      keyFormula = "Captions in Center Safe Zone + 2-3 Words Per Burst + Accent Color on Keywords",
      aiPrompts = listOf(
        "CapCut Auto-Caption style: Preset 'Bouncing Yellow' or 'Spring Pop', 2 lines max, centered.",
        "Sound FX cue list: Whoosh transition on scene cuts, deep bass hit on the hook, subtle tick-tock on countdowns."
      ),
      recommendedTools = listOf("CapCut (Desktop/Mobile - Top Free Choice)", "Opus Clip", "Submagic AI", "Premiere Pro Text-Based Editing"),
      commonMistakes = listOf(
        "Placing captions too low where YouTube's title and channel name completely cover them.",
        "Showing 10 words at once that crowd the screen.",
        "Forgetting sound effects on visual cuts (sound effects drive subconscious engagement)."
      ),
      checklistItem = "Checked safe zones and applied 2-3 word pop animated captions"
    ),

    GuidePhase(
      stepNumber = 6,
      title = "Music, Sound Design & Audio Mixing",
      timeEstimate = "5 mins",
      tag = "Audio Mix",
      summary = "Mix background tension music at -22dB with transition whooshes and bass drops to stimulate dopamine.",
      detailedExplanation = """
        Audio is 50% of the video experience.
        A flat voiceover with no music will cause viewers to swipe within 4 seconds.

        The Pro Audio Layering Stack:
        1. Main Voiceover: Normalized to -1.0 dB to -2.0 dB peak (loud and crystal clear).
        2. Background Music: Lower to -20 dB to -24 dB. It should be felt subconsciously rather than overpowering the voice.
        3. Sound Effects (SFX):
           • Whoosh / Swish: Every visual cut or text pop.
           • Deep Sub Bass Boom: On the opening hook reveal.
           • Riser / Sweep: During the 5 seconds leading to the climax.

        YouTube Sound Secret:
        You can also add a trending YouTube audio track in the YouTube app at 5-10% volume when uploading, which pushes your video to the audio's trend feed!
      """.trimIndent(),
      keyFormula = "Voice: -1.5dB | SFX: -8dB | Background Music: -22dB",
      aiPrompts = listOf(
        "Music search query: 'Dark ambient tension synth', 'Suspense documentary cinematic riser', 'Phonk bass instrumental low tempo'",
        "Free SFX sources: CapCut Sound Library, YouTube Audio Library, Pixabay Audio"
      ),
      recommendedTools = listOf("CapCut Audio Mixer", "Epidemic Sound", "YouTube Audio Library (100% Free)", "Pixabay Audio"),
      commonMistakes = listOf(
        "Background music is too loud and drowns out the AI voiceover.",
        "Using copyrighted music directly in the render instead of YouTube's native audio library.",
        "Sudden audio cut-offs at the end that break the seamless loop."
      ),
      checklistItem = "Voice normalized to -2dB, background music set to -22dB, and SFX inserted"
    ),

    GuidePhase(
      stepNumber = 7,
      title = "Publishing, SEO & Algorithm Triggers",
      timeEstimate = "5 mins",
      tag = "Launch",
      summary = "Select the killer thumbnail frame in the mobile app, write a sub-50 character title, and tag correctly.",
      detailedExplanation = """
        Publishing correctly on YouTube Shorts requires specific mobile app steps:

        1. Mobile App Upload (Crucial!):
           Always upload via the YouTube mobile app. Tap the pencil icon on the video preview to slide and pick the single most intriguing frame as your thumbnail! (Desktop upload doesn't allow custom Shorts thumbnails).
        2. The Click-Worthy Title:
           • Keep it under 45 characters so it doesn't get cut off on the mobile screen.
           • Include a curiosity gap: 'The Island Nobody Is Allowed To Visit' instead of 'North Sentinel Island History'.
           • Add #Shorts and 2 niche hashtags.
        3. Pinned Comment Hack:
           Immediately pin a question comment: 'Did you notice what happened at 0:24?' or 'Which theory do you believe? 1 or 2?'. Comments trigger the algorithm to push your Short further.
      """.trimIndent(),
      keyFormula = "Thumbnail Pencil Picker + Short Title (<45 chars) + Pinned Comment Engagement Loop",
      aiPrompts = listOf(
        "Give me 5 viral YouTube Shorts titles for a video about deep sea anomalies. Maximum 40 characters each, with high curiosity gaps.",
        "Write 3 engaging pinned comments to spark a debate in the comment section."
      ),
      recommendedTools = listOf("YouTube Mobile App (Pencil frame picker)", "TubeBuddy / vidIQ (Tag optimization)", "ShortsCraft Title Lab"),
      commonMistakes = listOf(
        "Uploading from desktop without setting the thumbnail frame via mobile.",
        "Using a boring generic title like 'Shorts video #4'.",
        "Uploading at 4K 60FPS with huge bitrate (YouTube compresses it poorly; 1080x1920 30/60fps at 15Mbps is optimal)."
      ),
      checklistItem = "Pencil thumbnail selected, title under 45 chars, and pinned comment ready"
    )
  )

  val viralHooks: List<HookTemplate> = listOf(
    HookTemplate(
      id = "h1",
      category = "Curiosity Gap",
      formula = "Scientists just discovered something in [Place] that shouldn't exist...",
      example = "Scientists just discovered a structure in Antarctica that shouldn't exist...",
      psychologicalTrigger = "Novelty & Fear of Unknown",
      retentionRate = "88% 3s Retention"
    ),
    HookTemplate(
      id = "h2",
      category = "Warning / Loss Aversion",
      formula = "If you ever see [Phenomenon], do NOT touch it...",
      example = "If you ever see purple sand on a beach, do NOT touch it...",
      psychologicalTrigger = "Survival Instinct & Urgency",
      retentionRate = "92% 3s Retention"
    ),
    HookTemplate(
      id = "h3",
      category = "Bizarre Paradox",
      formula = "This sounds completely fake, but [Mind-Blowing Fact]...",
      example = "This sounds completely fake, but sharks existed before trees...",
      psychologicalTrigger = "Cognitive Dissonance",
      retentionRate = "85% 3s Retention"
    ),
    HookTemplate(
      id = "h4",
      category = "Conspiracy / Secret Knowledge",
      formula = "There is a reason why [Authority/Entity] never talks about [Topic]...",
      example = "There is a reason why NASA abruptly stopped exploring the deep ocean...",
      psychologicalTrigger = "Forbidden Knowledge",
      retentionRate = "90% 3s Retention"
    ),
    HookTemplate(
      id = "h5",
      category = "AI / Tech Revelation",
      formula = "This AI tool is so dangerous that [Entity] tried to ban it...",
      example = "This new AI website is so powerful it feels completely illegal to know...",
      psychologicalTrigger = "Unfair Advantage & Tech Curiosity",
      retentionRate = "87% 3s Retention"
    ),
    HookTemplate(
      id = "h6",
      category = "Psychological Trick",
      formula = "If someone does [Action], here is what they are secretly thinking...",
      example = "If someone looks up and to the right, here is what their brain is doing...",
      psychologicalTrigger = "Interpersonal Curiosity & Empathy",
      retentionRate = "89% 3s Retention"
    ),
    HookTemplate(
      id = "h7",
      category = "Timeline Distortion",
      formula = "You are living closer in time to [Event A] than [Event B] was to [Event C]...",
      example = "Cleopatra lived closer in time to the iPhone than to the building of the Great Pyramids...",
      psychologicalTrigger = "Mind-Melting Perspective Shift",
      retentionRate = "84% 3s Retention"
    )
  )
}
