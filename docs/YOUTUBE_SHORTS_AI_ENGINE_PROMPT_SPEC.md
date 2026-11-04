# YouTube Shorts Autonomous Creation Engine — System Prompt & Architecture Specification

## 1. Architectural Overview & Pipeline Topology

The autonomous YouTube Shorts creation pipeline is designed as a deterministic, multi-stage state machine. It converts unstructured input (a topic, research snippet, or keyword) into a validated, render-ready **Edit Decision List (EDL)** with synchronized media assets.

```
┌────────────────────────────────────────────────────────────────────────┐
│                   AUTONOMOUS SHORTS ENGINE PIPELINE                    │
└────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│ STAGE 1: INGESTION & VIRAL TOPIC STRUCTURING                           │
│ - Input: Topic, Tone, Duration (15s / 30s / 60s), Target Persona       │
│ - Select proven hook framework (Curiosity Gap, Paradox, Loss Aversion) │
└────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│ STAGE 2: 4-PART SCRIPT & RETENTION ENGINEERING                         │
│ - 0:00 - 0:03: Spoken & Visual Pattern-Interrupt Hook                  │
│ - 0:03 - 0:15: Context Withholding / High-Tension Open Loop            │
│ - 0:15 - 0:45: Micro-Payoffs (visual change every 2.5 - 3.5 seconds)   │
│ - 0:45 - 0:60: Climax & The Seamless Loop Bridge                       │
│ - Cadence: Strict 155 - 175 Words Per Minute (WPM)                     │
└────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│ STAGE 3: ASSET API PAYLOAD ORCHESTRATION                               │
│ ┌──────────────────────────┐     ┌───────────────────────────────────┐ │
│ │ Text-to-Speech (TTS)     │     │ 9:16 Visual Prompts (Flux/MJ)     │ │
│ │ - SSML pause markers     │     │ - Negative prompt filters         │ │
│ │ - Word-timestamp enabled │     │ - Deterministic camera motion     │ │
│ └──────────────────────────┘     └───────────────────────────────────┘ │
└────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│ STAGE 4: EDIT DECISION LIST (EDL) COMPILATION                          │
│ - Multi-Track Timeline (Video, Voice, SFX, Music, Captions)            │
│ - Safe-Zone Bounding Box Enforcement (Captions at 35% - 60% Height)    │
│ - Millisecond-Accurate Timestamps for Headless Video Assemblers        │
└────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Production Master System Prompt

Copy and deploy the system prompt below into your LLM orchestration layer (OpenAI API, Anthropic Claude, Google Gemini, or LangChain/LlamaIndex agents):

```markdown
You are the Autonomous YouTube Shorts Directing & Editing Engine (ShortsDirector-v1).
Your role is to act as an elite short-form algorithmic scriptwriter, audio director, and post-production editor. You transform raw topics into fully orchestrated, production-ready 9:16 vertical video specifications that achieve >75% Viewed Rate (vs. Swiped Away) and >100% Average Percentage Viewed (via seamless looping).

---

### PIPELINE EXECUTION DIRECTIVES

You must execute the following 4 stages sequentially, outputting only a single, schema-compliant JSON document containing the complete Edit Decision List (EDL) and asset manifests.

#### STAGE 1: HOOK & SCRIPT RETENTION ARCHITECTURE
Every script must strictly adhere to the 4-part viral short architecture:
1. The 3-Second Pattern-Interrupt Hook (0:00 - 0:03):
   - Auditory: Shocking claim, counter-intuitive paradox, or survival warning. Never start with greetings, brand names, or introductions ("Hey guys", "In this video...").
   - Visual: High-motion cue (macro zoom, explosion, sudden glitch, or visual anomaly).
   - Proven Formulas:
     * Curiosity Gap: "Scientists just detected a signal in [Place] that shouldn't exist..."
     * Loss Aversion / Warning: "If you ever see [Phenomenon], do NOT touch it..."
     * Bizarre Paradox: "This sounds completely fake, but [Historical/Scientific Fact]..."
     * AI / Secret Knowledge: "This new AI discovery was immediately classified for one reason..."
2. The Open Loop & Tension Build (0:03 - 0:15):
   - Withhold the resolution. Reveal context in rapid, high-density increments. Never let the viewer deduce the climax early.
3. Micro-Payoffs & Rapid Visual Shifts (0:15 - 0:45):
   - A new visual cut or camera motion MUST occur every 2.5 to 3.5 seconds.
   - Dialogue cadence must maintain 155 to 175 Words Per Minute (WPM).
4. The Climax & Seamless Loop Bridge:
   - The final sentence must cut off cleanly without an outro or subscribe plea.
   - The final words must grammatically and phonetically fuse directly into the first word of the 0:00 hook line so that replay loops are imperceptible.

#### STAGE 2: VOICE SYNTHESIS (TTS) PROTOCOL
- Provide the full voiceover text formatted with SSML pauses (`<break time="250ms"/>` or em dashes `--`) before major reveals.
- Enforce speech pacing of 155–170 WPM.
- Specify voice style: Deep documentary narrative, energetic tech punch, or suspenseful thriller.
- Demand word-level alignment timestamps (`word_timestamps: true`) from the downstream TTS API (e.g., ElevenLabs / Google Cloud TTS).

#### STAGE 3: HYBRID VISUAL GENERATION & CAMERA MOTION
- For each scene, generate a hyper-specific visual prompt tailored for 9:16 vertical models (Flux.1 / Midjourney v6).
- Mandatory prompt syntax:
  `cinematic 9:16 vertical shot, [Subject], [Environment/Lighting], [Camera Angle], hyperrealistic, octane render, 8k, volumetric rays, shallow depth of field --ar 9:16`
- Negative prompts: `landscape, 16:9, text, watermark, bad anatomy, blurry, cartoonish, low resolution`
- Deterministic Camera Motion Cue:
  Assign an explicit motion profile to each static frame for the rendering engine:
  - `zoom_in`: Continuous push towards focal center (scale 1.0 -> 1.15).
  - `zoom_out`: Pull back revealing scale (scale 1.15 -> 1.0).
  - `pan_left` / `pan_right`: Subtle horizontal sweep across wide panoramic stills.
  - `speed_ramp_punch`: Rapid zoom-in on the first 0.3s followed by gentle drift.

#### STAGE 4: AUDIO LAYERING & SAFE-ZONE EDIT DECISION LIST (EDL)
- Audio Mixing Standards:
  - Voiceover Track: Mastered to peak between -1.0 dB and -2.0 dB.
  - Background Music: Ducked to -22 dB beneath narration.
  - Transition SFX: Whoosh, bass drop, or cinematic riser at -8 dB on every visual scene cut.
- Dynamic Subtitle Safe Zone:
  - YouTube UI elements cover the top 15% (search & audio title), bottom 20% (video title, channel avatar, subscribe button), and right 15% (like, comment, share, remix icons).
  - ALL subtitles must be centered vertically between 35% and 60% height (`y_position_percent: 48`).
  - Burst length: 1 to 3 words maximum per pop animation.
  - Keyword coloring: Primary white (#FFFFFF) with high-impact keywords accented in yellow (#FFE600) or neon green (#00FF66).

---

### OUTPUT JSON SCHEMA REQUIREMENT
You MUST reply with ONLY a single, valid JSON object matching the following structure:
{
  "project_metadata": {
    "title": "Short title under 45 characters",
    "duration_target_seconds": 30,
    "target_aspect_ratio": "9:16",
    "niche": "string",
    "retention_hook_formula": "string",
    "seamless_loop_bridge": "string",
    "estimated_wpm": 162,
    "seo_tags": ["#Shorts", "#Viral", "#NicheTag"]
  },
  "voiceover_synthesis": {
    "engine": "elevenlabs",
    "voice_id_recommended": "Adam_Deep_Narrative",
    "model": "eleven_multilingual_v2",
    "stability": 0.50,
    "clarity": 0.85,
    "ssml_text": "string with break tags",
    "plain_text": "clean text without tags"
  },
  "timeline_edl": {
    "video_tracks": [
      {
        "scene_id": 1,
        "start_time_ms": 0,
        "end_time_ms": 3200,
        "asset_prompt": "cinematic 9:16 vertical shot...",
        "negative_prompt": "string",
        "camera_motion": {
          "type": "zoom_in | zoom_out | pan_left | pan_right | speed_ramp_punch",
          "start_scale": 1.0,
          "end_scale": 1.15,
          "focus_point": {"x_percent": 50, "y_percent": 45}
        },
        "transition_in": {"type": "cut | whip_pan | glitch", "duration_ms": 200}
      }
    ],
    "sfx_tracks": [
      {
        "id": "sfx_1",
        "start_time_ms": 0,
        "name": "sub_bass_impact",
        "volume_db": -8.0,
        "description": "Deep cinematic impact on pattern interrupt"
      }
    ],
    "background_music": {
      "genre": "Dark ambient tension synth",
      "volume_db": -22.0,
      "duck_on_voice": true,
      "loop_seamlessly": true
    },
    "dynamic_captions": {
      "style": {
        "font_family": "Montserrat-Black",
        "font_size_pt": 48,
        "vertical_align_percent": 48,
        "horizontal_align": "center",
        "text_case": "UPPERCASE",
        "primary_color": "#FFFFFF",
        "accent_color": "#FFE600",
        "outline_color": "#000000",
        "outline_width_px": 6,
        "shadow_blur_px": 8,
        "animation_type": "word_pop_scale"
      },
      "word_bursts": [
        {
          "start_time_ms": 0,
          "end_time_ms": 1200,
          "text": "YOUR BRAIN IS",
          "accent_word": ""
        },
        {
          "start_time_ms": 1200,
          "end_time_ms": 2500,
          "text": "LYING TO YOU",
          "accent_word": "LYING"
        }
      ]
    }
  }
}
```

---

## 3. Formal JSON Schema (Validation Contract)

Downstream video rendering engines (FFmpeg bash workers, Remotion compositions, or Shotstack pipelines) validate the payload against this JSON Schema:

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "YouTubeShortsEDL",
  "type": "object",
  "required": ["project_metadata", "voiceover_synthesis", "timeline_edl"],
  "properties": {
    "project_metadata": {
      "type": "object",
      "required": ["title", "duration_target_seconds", "target_aspect_ratio", "retention_hook_formula", "seamless_loop_bridge"],
      "properties": {
        "title": { "type": "string", "maxLength": 50 },
        "duration_target_seconds": { "type": "integer", "minimum": 10, "maximum": 60 },
        "target_aspect_ratio": { "type": "string", "enum": ["9:16"] },
        "niche": { "type": "string" },
        "retention_hook_formula": { "type": "string" },
        "seamless_loop_bridge": { "type": "string" },
        "estimated_wpm": { "type": "integer", "minimum": 140, "maximum": 190 },
        "seo_tags": { "type": "array", "items": { "type": "string" } }
      }
    },
    "voiceover_synthesis": {
      "type": "object",
      "required": ["engine", "ssml_text", "plain_text"],
      "properties": {
        "engine": { "type": "string" },
        "voice_id_recommended": { "type": "string" },
        "model": { "type": "string" },
        "stability": { "type": "number", "minimum": 0.0, "maximum": 1.0 },
        "clarity": { "type": "number", "minimum": 0.0, "maximum": 1.0 },
        "ssml_text": { "type": "string" },
        "plain_text": { "type": "string" }
      }
    },
    "timeline_edl": {
      "type": "object",
      "required": ["video_tracks", "sfx_tracks", "background_music", "dynamic_captions"],
      "properties": {
        "video_tracks": {
          "type": "array",
          "items": {
            "type": "object",
            "required": ["scene_id", "start_time_ms", "end_time_ms", "asset_prompt", "camera_motion"],
            "properties": {
              "scene_id": { "type": "integer" },
              "start_time_ms": { "type": "integer" },
              "end_time_ms": { "type": "integer" },
              "asset_prompt": { "type": "string" },
              "negative_prompt": { "type": "string" },
              "camera_motion": {
                "type": "object",
                "required": ["type", "start_scale", "end_scale"],
                "properties": {
                  "type": { "type": "string", "enum": ["zoom_in", "zoom_out", "pan_left", "pan_right", "speed_ramp_punch"] },
                  "start_scale": { "type": "number" },
                  "end_scale": { "type": "number" },
                  "focus_point": {
                    "type": "object",
                    "properties": {
                      "x_percent": { "type": "number" },
                      "y_percent": { "type": "number" }
                    }
                  }
                }
              },
              "transition_in": {
                "type": "object",
                "properties": {
                  "type": { "type": "string" },
                  "duration_ms": { "type": "integer" }
                }
              }
            }
          }
        },
        "sfx_tracks": {
          "type": "array",
          "items": {
            "type": "object",
            "required": ["id", "start_time_ms", "name", "volume_db"],
            "properties": {
              "id": { "type": "string" },
              "start_time_ms": { "type": "integer" },
              "name": { "type": "string" },
              "volume_db": { "type": "number" },
              "description": { "type": "string" }
            }
          }
        },
        "background_music": {
          "type": "object",
          "required": ["genre", "volume_db", "duck_on_voice", "loop_seamlessly"],
          "properties": {
            "genre": { "type": "string" },
            "volume_db": { "type": "number" },
            "duck_on_voice": { "type": "boolean" },
            "loop_seamlessly": { "type": "boolean" }
          }
        },
        "dynamic_captions": {
          "type": "object",
          "required": ["style", "word_bursts"],
          "properties": {
            "style": {
              "type": "object",
              "required": ["font_family", "font_size_pt", "vertical_align_percent", "primary_color", "accent_color"],
              "properties": {
                "font_family": { "type": "string" },
                "font_size_pt": { "type": "integer" },
                "vertical_align_percent": { "type": "number", "minimum": 35, "maximum": 60 },
                "horizontal_align": { "type": "string" },
                "text_case": { "type": "string" },
                "primary_color": { "type": "string" },
                "accent_color": { "type": "string" },
                "outline_color": { "type": "string" },
                "outline_width_px": { "type": "integer" },
                "shadow_blur_px": { "type": "integer" },
                "animation_type": { "type": "string" }
              }
            },
            "word_bursts": {
              "type": "array",
              "items": {
                "type": "object",
                "required": ["start_time_ms", "end_time_ms", "text"],
                "properties": {
                  "start_time_ms": { "type": "integer" },
                  "end_time_ms": { "type": "integer" },
                  "text": { "type": "string" },
                  "accent_word": { "type": "string" }
                }
              }
            }
          }
        }
      }
    }
  }
}
```

---

## 4. Production Example Output (30-Second Space Short)

Below is an exact payload generated by this prompt for the topic *"The Great Attractor"*:

```json
{
  "project_metadata": {
    "title": "The Cosmic Monster Dragging Our Galaxy",
    "duration_target_seconds": 30,
    "target_aspect_ratio": "9:16",
    "niche": "Cosmic Mystery & Astronomy",
    "retention_hook_formula": "Pattern Interrupt & Imminent Threat",
    "seamless_loop_bridge": "...and that is because our entire galaxy is moving at 1.3 million miles per hour towards this!",
    "estimated_wpm": 162,
    "seo_tags": ["#Shorts", "#Space", "#NASA", "#Universe", "#Astronomy"]
  },
  "voiceover_synthesis": {
    "engine": "elevenlabs",
    "voice_id_recommended": "Adam_Deep_Suspense",
    "model": "eleven_multilingual_v2",
    "stability": 0.48,
    "clarity": 0.85,
    "ssml_text": "Our entire galaxy is moving at 1.3 million miles per hour towards this... <break time=\"200ms\"/> Astronomers call it The Great Attractor--a colossal gravitational anomaly hidden behind the core of the Milky Way. It has the mass of tens of thousands of galaxies, pulling billions of stars into the unknown. And no telescope can see inside, because...",
    "plain_text": "Our entire galaxy is moving at 1.3 million miles per hour towards this. Astronomers call it The Great Attractor, a colossal gravitational anomaly hidden behind the core of the Milky Way. It has the mass of tens of thousands of galaxies, pulling billions of stars into the unknown. And no telescope can see inside, because..."
  },
  "timeline_edl": {
    "video_tracks": [
      {
        "scene_id": 1,
        "start_time_ms": 0,
        "end_time_ms": 3800,
        "asset_prompt": "cinematic 9:16 vertical shot, luminous spiral Milky Way galaxy rotating in velvet space, speed motion blur, hyperrealistic, octane render, 8k, volumetric stars --ar 9:16",
        "negative_prompt": "landscape, 16:9, text, blurry, low resolution",
        "camera_motion": {
          "type": "speed_ramp_punch",
          "start_scale": 1.0,
          "end_scale": 1.25,
          "focus_point": { "x_percent": 50, "y_percent": 50 }
        },
        "transition_in": { "type": "cut", "duration_ms": 0 }
      },
      {
        "scene_id": 2,
        "start_time_ms": 3800,
        "end_time_ms": 11500,
        "asset_prompt": "cinematic 9:16 vertical shot, massive gravitational lens distorting starlight into glowing violet and golden rings in deep cosmic void, 8k render, cinematic lighting --ar 9:16",
        "negative_prompt": "landscape, 16:9, cartoon, watermark",
        "camera_motion": {
          "type": "zoom_in",
          "start_scale": 1.0,
          "end_scale": 1.15,
          "focus_point": { "x_percent": 50, "y_percent": 45 }
        },
        "transition_in": { "type": "whip_pan", "duration_ms": 180 }
      },
      {
        "scene_id": 3,
        "start_time_ms": 11500,
        "end_time_ms": 20200,
        "asset_prompt": "cinematic 9:16 vertical shot, cosmic web of glowing galactic filaments converging into a terrifying dark gravitational singularity, volumetric deep space dust, 8k --ar 9:16",
        "negative_prompt": "landscape, 16:9, low quality, oversaturated",
        "camera_motion": {
          "type": "pan_left",
          "start_scale": 1.1,
          "end_scale": 1.1,
          "focus_point": { "x_percent": 60, "y_percent": 50 }
        },
        "transition_in": { "type": "glitch", "duration_ms": 150 }
      },
      {
        "scene_id": 4,
        "start_time_ms": 20200,
        "end_time_ms": 30000,
        "asset_prompt": "cinematic 9:16 vertical shot, golden honeycomb mirrors of James Webb telescope reflecting colossal black hole event horizon, cinematic rim lighting, 8k --ar 9:16",
        "negative_prompt": "landscape, 16:9, blurry, flat lighting",
        "camera_motion": {
          "type": "zoom_in",
          "start_scale": 1.0,
          "end_scale": 1.2,
          "focus_point": { "x_percent": 50, "y_percent": 40 }
        },
        "transition_in": { "type": "whip_pan", "duration_ms": 200 }
      }
    ],
    "sfx_tracks": [
      {
        "id": "sfx_1",
        "start_time_ms": 0,
        "name": "deep_sub_bass_impact",
        "volume_db": -8.0,
        "description": "Shock impact on opening galaxy reveal"
      },
      {
        "id": "sfx_2",
        "start_time_ms": 3800,
        "name": "whip_pan_whoosh",
        "volume_db": -10.0,
        "description": "Fast air whoosh on scene transition"
      },
      {
        "id": "sfx_3",
        "start_time_ms": 11500,
        "name": "gravitational_distortion_hum",
        "volume_db": -9.0,
        "description": "Low drone resonance"
      },
      {
        "id": "sfx_4",
        "start_time_ms": 26000,
        "name": "cliffhanger_riser_sharp_cut",
        "volume_db": -7.5,
        "description": "High tension synth riser snapping abruptly into seamless loop"
      }
    ],
    "background_music": {
      "genre": "Dark cinematic ambient suspense synth",
      "volume_db": -22.0,
      "duck_on_voice": true,
      "loop_seamlessly": true
    },
    "dynamic_captions": {
      "style": {
        "font_family": "Montserrat-Black",
        "font_size_pt": 46,
        "vertical_align_percent": 48.0,
        "horizontal_align": "center",
        "text_case": "UPPERCASE",
        "primary_color": "#FFFFFF",
        "accent_color": "#FFE600",
        "outline_color": "#000000",
        "outline_width_px": 6,
        "shadow_blur_px": 10,
        "animation_type": "word_pop_scale"
      },
      "word_bursts": [
        { "start_time_ms": 0, "end_time_ms": 1200, "text": "OUR GALAXY IS", "accent_word": "" },
        { "start_time_ms": 1200, "end_time_ms": 2400, "text": "MOVING AT", "accent_word": "" },
        { "start_time_ms": 2400, "end_time_ms": 3800, "text": "1.3 MILLION MPH", "accent_word": "1.3 MILLION" },
        { "start_time_ms": 3800, "end_time_ms": 5500, "text": "TOWARDS THIS...", "accent_word": "THIS" },
        { "start_time_ms": 5500, "end_time_ms": 7800, "text": "THE GREAT ATTRACTOR", "accent_word": "ATTRACTOR" },
        { "start_time_ms": 7800, "end_time_ms": 11500, "text": "HIDDEN BEHIND STARS", "accent_word": "HIDDEN" },
        { "start_time_ms": 11500, "end_time_ms": 15000, "text": "10,000 GALAXIES", "accent_word": "10,000" },
        { "start_time_ms": 15000, "end_time_ms": 20200, "text": "PULLING EVERYTHING IN", "accent_word": "PULLING" },
        { "start_time_ms": 20200, "end_time_ms": 25000, "text": "NO TELESCOPE SEES", "accent_word": "NO" },
        { "start_time_ms": 25000, "end_time_ms": 30000, "text": "AND THAT IS BECAUSE...", "accent_word": "BECAUSE" }
      ]
    }
  }
}
```
