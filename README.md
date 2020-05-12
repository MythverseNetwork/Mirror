# Mirror
Integrate with Discord to allow for chat mirroring.

## Features
- Minecraft chat mirroring - takes the global network chat and outputs it to a specified channel using a webhook.
- Member count status - updates a channel in Discord with the current member count.
- Ratelimit support - is able to handle ratelimit errors

## Configuration
Below is an example configuration:

```yaml
token: "my secret token"

mirror:
    webhookUrl: "https://discordapp.com/webhooks/somewebhook/token"

memberCount:
    channel: "123456789"

language:
    # Available paramters:
    # %i - the current count of online players.
    memberCountFormat: "Currently online: %i"
    mirrorFormat: "[%s] %s: %s"
```

## License

Licensed under the MIT license. See `LICENSE` for more information.