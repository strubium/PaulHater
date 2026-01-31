# Paul Hater

Removes the warning from the log when SoundSystem shuts down as its commonly the final log.

## What
Prevents this 

`[Client thread/WARN] [net.minecraft.client.audio.SoundManager]: Author: Paul Lamb, www.paulscode.com`

from being sent using ASM. It does this by visiting `SoundSystem.java` and replacing its `importantMessage` method with a dummy that does nothing. Will not affect wider error logging as it doesn't modify the `SoundSystemLogger` class proper   

## Why
It's annoying. Paul, you made an excellent sound system, but you don't need to tell me when I shut down the game 
