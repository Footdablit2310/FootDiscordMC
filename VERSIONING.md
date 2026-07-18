# Versioning rules applied to Foot: Organic Processing
## Versioning scheme
Foot: Organic Processing uses the O.M.S.H versioning scheme(also very similar Epoch SemVer but with a few modifications). The version number is in the format of `O.M.S.H` where:
- `O` is the overhaul version, which is incremented when there are changes that are not backward compatible with all the previous version's, like code changes and file structure. Any code that uses the API is also affected. So Overhaul version means external and internal implematation and code changes which is why porting to these version from previus version is going to be hard. A Overhaul version doesnt mean there that there is going to be a lot of changes it just means no backward compatibility.
- `M` is the major version, which is incremented when there are significant new features or improvements that are backward compatible.
- `S` is the semi-major/big-minor version, which is incremented when there are a lot of changes but doesn't qualify as a major version.
- `H` is the hotfix/patch/minor version, which is incremented when there are urgent bug fixes/minor improvements or hotfixes.