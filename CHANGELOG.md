# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-02-21

### Added

#### AI Code Generation
- **AI Code Generator Service** - Core AI-powered code generation functionality
  - `AiCodeGeneratorService` - Abstract service interface for code generation
  - `AiCodeGeneratorServiceFactory` - Factory pattern for creating generator instances
  - Support for HTML and multi-file code generation

- **Code Generation Models**
  - `HtmlCodeResult` - Model for HTML code generation results
  - `MultiFileCodeResult` - Model for multi-file code generation results
  - `CodeGenTypeEnum` - Enumeration for code generation types

- **Code Parser System**
  - `CodeParser` - Abstract parser interface
  - `CodeParserExecutor` - Executor for running parsers
  - `HtmlCodeParser` - Parser for HTML code
  - `MultiFileCodeParser` - Parser for multi-file projects

- **Code File Saver System**
  - `CodeFileSaverTemplate` - Template pattern for file saving
  - `CodeFileSaverExecutor` - Executor for saving code files
  - `HtmlCodeFileSaverTemplate` - Saver for HTML code
  - `MultiFileCodeFileSaverTemplate` - Saver for multi-file projects

- **AI Code Generator Facade**
  - `AiCodeGeneratorFacade` - Unified interface for code generation workflow

- **Prompt Templates**
  - `codegen-html-system-prompt.txt` - System prompt for HTML generation
  - `codegen-multi-file-system-prompt.txt` - System prompt for multi-file generation

#### User Interface
- **Responsive Header Component**
  - Multi-breakpoint responsive design (xl, lg, md, sm, xs)
  - Mobile drawer menu for navigation
  - Adaptive title display (full/short version)
  - Sticky header with smooth transitions

- **Design System**
  - Unified color scheme using CSS variables
  - Primary color: Teal (#0d9488)
  - Consistent border radius, shadows, and spacing
  - Custom CSS variables for theming

### Changed

#### UI/UX Improvements
- **Login Button**
  - Changed color from purple (#8b5cf6) to primary teal (#0d9488)
  - Fixed position on the right side of header
  - Improved hover and active states

- **User Avatar**
  - Added teal border for visual consistency
  - Fixed display across all screen sizes
  - Maintained click interaction with dropdown menu

- **Navigation Menu**
  - Changed alignment from center to left
  - Added responsive padding adjustments
  - Improved mobile menu styling

- **Mobile Drawer**
  - Removed duplicate login button
  - Added styled user section for logged-in users
  - Unified menu item styling with main theme

### Fixed

- Spring Security dependency version conflict
- SQL integrity constraint violations for time fields
- Header title overflow on narrow screens
- Login button visibility on mobile devices

### Security

- Password encryption using Spring Security Crypto
- Rate limiting for API endpoints
- Input sanitization for XSS prevention
- Session management with configurable timeout

---

## Version History

| Version | Date | Description |
|---------|------|-------------|
| 1.0.0 | 2026-02-21 | Initial release with AI code generation |
| 0.1.0 | 2026-02-20 | Project initialization |
