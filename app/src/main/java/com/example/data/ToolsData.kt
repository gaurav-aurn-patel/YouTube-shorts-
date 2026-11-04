package com.example.data

import com.example.model.AIToolInfo

object ToolsData {

  val aiTools: List<AIToolInfo> = listOf(
    AIToolInfo(
      id = "capcut",
      name = "CapCut",
      category = "Video Editor & Captions",
      badge = "Must Have",
      rating = 4.9f,
      isFreeTierAvailable = true,
      description = "The industry standard editor for YouTube Shorts and TikTok. Features free auto-captions with viral animations, sound FX library, and speed ramping.",
      recommendedWorkflow = "Import AI voiceover + B-roll images/videos -> Auto-generate captions -> Choose 'Spring Pop' style -> Add whoosh SFX on cuts -> Export 1080x1920 60fps.",
      proTip = "Use CapCut's desktop version for faster keyboard shortcuts (Q and W for ripple trims). Always toggle 'Smart Cut' to remove silent pauses.",
      samplePromptOrSetting = "Preset: Auto Captions -> Dynamic Template -> Highlight Color #FFD700"
    ),

    AIToolInfo(
      id = "elevenlabs",
      name = "ElevenLabs",
      category = "AI Voiceover",
      badge = "Industry Leader",
      rating = 4.9f,
      isFreeTierAvailable = true,
      description = "Hyper-realistic text-to-speech with natural breathing, emotional inflection, and hundreds of voice clones. Indistinguishable from human voice actors.",
      recommendedWorkflow = "Paste your 30s-60s script -> Select 'Adam' (deep narrative) or 'Rachel' (clear engaging) -> Stability 50%, Clarity 80% -> Generate MP3.",
      proTip = "Add ellipses (...) or em dashes (--) to introduce dramatic micro-pauses before shocking facts. Speeds up voice naturalness by 2x.",
      samplePromptOrSetting = "Voice: Adam (Narrative Deep) | Model: Eleven Multilingual v2 | Speed: 1.05x"
    ),

    AIToolInfo(
      id = "midjourney",
      name = "Midjourney v6",
      category = "AI Visuals",
      badge = "Best Visuals",
      rating = 4.8f,
      isFreeTierAvailable = false,
      description = "Top tier photorealistic image generator. Perfect for generating striking, dramatic vertical stills for history, space, and science shorts.",
      recommendedWorkflow = "Generate vertical stills with `--ar 9:16` -> Upscale best variations -> Animate in Runway or CapCut with 3D Zoom.",
      proTip = "Always specify lighting and camera in prompt: 'volumetric lighting, 35mm lens, octane render, 8k, cinematic color grade --ar 9:16 --v 6.0'.",
      samplePromptOrSetting = "cinematic 9:16 vertical shot, ancient glowing underwater ruins, volumetric bioluminescence --ar 9:16 --v 6.0"
    ),

    AIToolInfo(
      id = "flux",
      name = "Flux.1 (Schnell / Dev)",
      category = "AI Visuals",
      badge = "Free & Open",
      rating = 4.8f,
      isFreeTierAvailable = true,
      description = "Next-generation open-weights image generator from Black Forest Labs. Handles text on images and photorealistic humans better than older models.",
      recommendedWorkflow = "Use free on HuggingFace or Replicate -> Set aspect ratio to 9:16 -> Download high-res PNG for video generation.",
      proTip = "Flux excels at rendering accurate text on road signs, newspapers, and computer screens inside your video scenes.",
      samplePromptOrSetting = "macro photo, 9:16 vertical, futuristic holographic smartphone showing crypto chart crashing, hyperrealistic, depth of field"
    ),

    AIToolInfo(
      id = "runway",
      name = "Runway Gen-3 Alpha",
      category = "AI Video Generation",
      badge = "Hollywood Grade",
      rating = 4.7f,
      isFreeTierAvailable = true,
      description = "Generates fluid 5 to 10-second cinematic video clips from text or still images with precise camera movement controls.",
      recommendedWorkflow = "Upload your Midjourney/Flux still -> Set camera motion (Push in, Pan, Orbit) -> Generate 5-second video clip -> Speed ramp in editor.",
      proTip = "Use Image-to-Video mode instead of pure text-to-video for 10x better consistency and composition control.",
      samplePromptOrSetting = "Camera motion: Dolly in 4, slight tilt up, slow motion smoke drifting, cinematic lighting"
    ),

    AIToolInfo(
      id = "opusclip",
      name = "Opus Clip",
      category = "Long-to-Shorts Repurposing",
      badge = "Viral Clipper",
      rating = 4.7f,
      isFreeTierAvailable = true,
      description = "AI that takes long podcasts or YouTube videos and automatically detects viral moments, crops to 9:16, adds auto-captions, and scores virality.",
      recommendedWorkflow = "Drop any YouTube URL (podcast, interview, tech keynote) -> Opus clips the top 10 moments with viral scores 0-99 -> Export ready-to-post Shorts.",
      proTip = "Look for clips with a virality score above 85. Check that the speaker's face tracking is centered before downloading.",
      samplePromptOrSetting = "Auto Speaker Detection + Alex Hormozi Caption Preset"
    ),

    AIToolInfo(
      id = "submagic",
      name = "Submagic",
      category = "AI Auto-Captions & B-Roll",
      badge = "Creator Favorite",
      rating = 4.6f,
      isFreeTierAvailable = true,
      description = "AI tool specifically built for Shorts: auto-generates dynamic captions with automated emojis, sound effects, and auto-inserted b-roll stock clips.",
      recommendedWorkflow = "Upload raw talking-head or voiceover video -> AI detects keywords -> Adds emojis + SFX + B-roll clips automatically.",
      proTip = "Customize your brand colors in Submagic so all your Shorts have instantly recognizable neon subtitles.",
      samplePromptOrSetting = "Preset: MrBeast Subtitles + Auto Sound Effects on Transitions"
    ),

    AIToolInfo(
      id = "invideo",
      name = "InVideo AI",
      category = "All-In-One Generator",
      badge = "One-Prompt Video",
      rating = 4.5f,
      isFreeTierAvailable = true,
      description = "Creates entire YouTube Shorts from a single text prompt: writes the script, generates the voiceover, selects stock footage and AI clips, and adds captions.",
      recommendedWorkflow = "Type prompt: 'Create a 45-second YouTube Short about how black holes form, dark suspense tone' -> InVideo renders complete draft in 2 mins.",
      proTip = "Use InVideo for rapid prototyping, then swap 2 or 3 stock clips with custom Midjourney/Flux generations to make the quality elite.",
      samplePromptOrSetting = "Prompt: 30s YouTube Short on psychology secrets, voice: deep male American, subtitles: bold white and yellow"
    )
  )

  val tenMinuteWorkflowSteps: List<String> = listOf(
    "1. Ideate (1 min): Choose high-retention niche in ShortsCraft AI and generate 30s script.",
    "2. Voiceover (1 min): Copy voiceover text into ElevenLabs or CapCut TTS with natural pauses.",
    "3. Visuals (3 mins): Copy Midjourney/Flux scene prompts from ShortsCraft AI, render 6-8 vertical images.",
    "4. Motion (2 mins): Apply Runway Gen-3 camera dolly or CapCut 3D Zoom to make stills move.",
    "5. Edit & Captions (2 mins): Add auto-captions in CapCut (centered safe zone), whoosh sound effects on cuts.",
    "6. Publish (1 min): Upload in YouTube Mobile App, slide pencil to best frame, paste sub-45 char title & #Shorts."
  )
}
