# jeu-Undercover
## Overview

This repository contains the implementation of the game **Undercover** in Java. The game is a multiplayer party game where players take on different roles (Civilians, Undercover agents, and Mr. White), 
and through a series of discussions and votes, they must identify the undercover players before they are eliminated. The game combines logic, deduction, and strategy to create a fun and engaging experience 
for all participants.

## Game Rules

- **Civilians** must work together to identify the **Undercover** players and **Mr. White**.
- **Undercover** players must blend in with the civilians while secretly eliminating them.
- **Mr. White** has a special role and must avoid being caught.
- The game is divided into **discussion phases** where players communicate, followed by **voting phases** where players decide who to eliminate.

## Features

- **Player Roles**: Players can be assigned one of three roles: Civile (Civilian), Undercover, or Mr. White.
- **Discussion Forum**: Players participate in discussions to share information and attempt to discover the Undercover players.
- **Voting System**: After the discussion, a voting phase occurs, and players vote to eliminate one player. The player with the most votes is eliminated.
- **Game Administration**: The game can be administered by an administrator who can start new games, assign roles, and track statistics.
- **Win Conditions**: The game ends when all Undercover players are eliminated, or when they outnumber the Civilians.

## Project Structure

This Java project is organized into the following packages:

### 1. `GestionDeJoueur`
- **Joueur**: Represents a player in the game.
- **Civile**: Inherits from `Joueur` and represents a Civilian.
- **Undercover**: Inherits from `Joueur` and represents an Undercover player.
- **MrWhite**: Inherits from `Joueur` and represents Mr. White.
- **GestionJoueur**: Handles the management of players, including role assignment and player registration.

### 2. `Forum`
- **Message**: Represents a message posted during the discussion phase.
- **Discussion**: Manages the discussion threads between players.
- **Forum**: Coordinates the forum where players post messages and communicate.
- **DiscussionRoom**: Represents the discussion space for players.
- **HistoriqueMessage**: Stores the history of all messages exchanged in the forum.

### 3. `Vote`
- **Vote**: Represents a vote cast by a player.
- **GestionVotes**: Manages the voting process.
- **Elimination**: Handles the elimination of players after each vote.
- **PhaseVote**: Represents the voting phase of the game.
- **StatiqueVote**: Collects statistics on voting behavior.

### 4. `Administration`
- **Administrateur**: The game administrator responsible for overseeing the game.
- **GestionPartie**: Manages the flow of the game, from start to finish.
- **Gagnant**: Declares the winning team at the end of the game.
- **GestionRole**: Handles the assignment of roles to players.
- **GestionScore**: Tracks player scores and performance.
