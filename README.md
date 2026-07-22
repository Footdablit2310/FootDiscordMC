# FootDiscordMC
## Packet format
```
{
    "command": "KICK | BAN | WHITELIST | BROADCAST",
    "subcommand": "optional string",
    "data": {
    "player": "PlayerName",
    "reason": "Reason text"   // used for KICK/BAN
    // OR
    "msg": "Message text"     // used for BROADCAST
}
```
This format was made to have MAXIMUM flexibility with minimal complexity.