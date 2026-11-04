# ShortsCraft AI — Official Standalone Website & Web Portfolio

An end-to-end plan to generate a standalone, single-file HTML/CSS/JavaScript website bundle (`web/index.html` and root distribution) that serves as the official landing page and creator portfolio for **ShortsCraft AI**, while maintaining the integrity and compilation of the existing Android project.

---

### User Review & Critical Decisions

> [!IMPORTANT]
> The implementation strictly adheres to the choices confirmed during clarification:
> - **Delivery Mode**: Generate standalone, production-ready website files (`HTML`, `CSS`, and vanilla `JavaScript`) with zero external build step dependencies (ready to open directly in any browser or deploy to GitHub Pages, Netlify, Vercel, or Cloud Run).
> - **Primary Purpose**: Official product landing page and interactive web portfolio showcasing ShortsCraft AI's 7-step viral engine, safe-zone simulator, and automated video pipeline.
> - **Export Format**: Complete, self-contained single-file HTML bundle with embedded CSS styling, responsive layout, and client-side interactive tools (Script Generator demo, Title Evaluator, and 9:16 Safe Zone Simulator).
> - **Android Compatibility**: All web assets will reside in `/web/` and `/app/src/main/assets/web/` so the Android application continues to build and compile cleanly without conflicts.

---

### 1. Overview & Core Concept

#### What It Delivers
A modern, dark-mode web application and marketing landing page for **ShortsCraft AI**, featuring:
1. **Hero Section**: Animated typography, 3D neon gradient backdrop, call-to-action buttons, and live algorithm retention statistics (>75% Viewed Rate, >100% Loop APV).
2. **Interactive 9:16 Shorts Safe Zone Simulator**: A live CSS smartphone viewport showing YouTube's native UI overlay collision zones (search, like, comment, sound disc, title).
3. **Web AI Script Generator (Interactive Demo)**: Client-side interactive script builder allowing web visitors to select a niche, duration, and tone to generate viral scripts with visual prompts and seamless loop endings.
4. **Viral Hook Lab & Title Rater**: Real-time JavaScript title evaluation calculating viral score, mobile character cutoff warnings, and emotional triggers.
5. **Interactive 7-Step Masterclass Roadmap**: Visual timeline explaining the step-by-step AI video production workflow.
6. **Automated Pipeline & Architecture Spec**: Dedicated technical section showcasing the JSON Edit Decision List (EDL) engine schema.

#### Target Audience
YouTube creators, automation channel operators, media agencies, and developers seeking an automated AI-driven short-form video workflow.

---

### 2. User Experience & Visual Design

#### Visual Identity & Theme
- **Theme**: Luxury Dark Studio (`#0B0A10` deep space canvas, `#141320` elevated cards, `#1E1B2E` borders).
- **Accents**: YouTube Shorts Crimson (`#FF0033`), Neon Violet (`#8B5CF6`), Electric Cyan (`#06B6D4`), and Amber Gold (`#F59E0B`).
- **Typography**: Google Font `Outfit` & `Inter` loaded via CDN for high-legibility display headers and clean body text.
- **Micro-interactions**: CSS glow effects on hover, smooth scrolling anchors, tab switching, and one-click copy notifications.

```
┌────────────────────────────────────────────────────────────────────────┐
│                   SHORTSCRAFT AI WEB LANDING PAGE                      │
├────────────────────────────────────────────────────────────────────────┤
│ [Nav] Logo  •  Features  •  Script Demo  •  Safe Zones  •  EDL Spec    │
├────────────────────────────────────────────────────────────────────────┤
│                                                                        │
│   HERO SECTION                                                         │
│   "Automate Viral YouTube Shorts with AI"                              │
│   [Try Script Demo]  [View Safe Zone Simulator]                        │
│                                                                        │
├──────────────────────────────────────┬─────────────────────────────────┤
│ INTERACTIVE WEB SCRIPT GENERATOR     │ 9:16 SMARTPHONE SIMULATOR       │
│ - Niche: [Space] [Tech] [Facts]      │ ┌─────────────────────────────┐ │
│ - Duration: [15s] [30s] [60s]        │ │ ⚠️ Top Overlay (Search Bar) │ │
│ - Tone: [Dramatic] [Mysterious]      │ │                             │ │
│ - [Generate Script Button]           │ │ ✅ 100% Safe Zone (Captions)│ │
│                                      │ │                             │ │
│ Output:                              │ │ ⚠️ Bottom Overlay (Title)   │ │
│ • 0-3s Hook • Scenes • Loop Bridge   │ └─────────────────────────────┘ │
├──────────────────────────────────────┴─────────────────────────────────┤
│ VIRAL TITLE RATER & HOOK LAB                                           │
│ Input: "Why scientists are terrified..." ➔ Score: 94/100 (Optimal)     │
├────────────────────────────────────────────────────────────────────────┤
│ 7-STEP AI PRODUCTION ROADMAP (Voiceover, Visuals, B-Roll, Captions)   │
├────────────────────────────────────────────────────────────────────────┤
│ AUTOMATED ENGINE JSON EDL SPECIFICATION & COPY SNIPPET                 │
└────────────────────────────────────────────────────────────────────────┘
```

---

### 3. Key Product Decisions & Trade-Offs

- **Decision 1: Zero External Build Tools (Self-Contained HTML/CSS/JS)**
  - *Chosen Approach*: Write a single, pristine `web/index.html` with embedded CSS and modern modular ES6 JavaScript.
  - *Why*: Allows anyone to double-click and view the site locally without `npm install` or node dev servers. Enables instantaneous zero-config hosting on GitHub Pages, Netlify, or static web servers.
  - *Trade-off*: Avoids complex React/Next.js hydration pipelines in this turn while delivering full interactive UI state.

- **Decision 2: Dual Placement (`web/index.html` and Android Web Assets)**
  - *Chosen Approach*: Save the website bundle to `/web/index.html` for direct web deployment, and mirror into `app/src/main/assets/web/` so Android builds and potential WebView wrappers continue to function seamlessly.
  - *Why*: Preserves Android Gradle compliance and allows the web landing page to be exported directly via AI Studio's file explorer.

---

### 4. Technical Architecture & File Plan

- **`web/index.html`**: The complete standalone single-file production website containing:
  - Responsive HTML5 layout with mobile-first CSS media queries.
  - Embedded CSS custom properties (`--bg-primary`, `--accent-red`, `--accent-purple`, `--accent-cyan`).
  - Interactive JavaScript engines:
    - Client-side Script Generation Engine (matching Android `ScriptEngine.kt`).
    - Real-time Title Analyzer (calculating viral score, length, and emotional triggers).
    - Safe Zone toggle and interactive subtitle simulator.
    - Clipboard copy helper with floating toast notifications.
- **Verification**: Run `compile_applet` and `:app:testDebugUnitTest` to guarantee the Android applet remains 100% build-clean and operational alongside the new web distribution.
