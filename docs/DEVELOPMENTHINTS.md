# Development Hints

## Check Plugin Python Script

The Python script `check_plugin.py` can be used to speed up development. The script is located in Demo App root directory.

The script performs the following tasks for the honeywell plugin project:
1. Extract the action from all files unter `/actions/`.
2. Basic checking for correct naming scheme of `ACTION_NAME`. A valid `ACTION_NAME` is
	```
	public static final String ACTION_NAME = "nameOfTheAction";
	```
3. Update honeywell project `honeywell.js` with all actions.
4. Update the honeywell project `plugin.xml` with all source files.

You can also update the Honeywell plugin in the Cordova Demo project located under path with the additional command line argument `--reload`.

## Add and remove plugins during development

### Error messsage 'UnhandledPromiseRejectionWarning'

If you try to add and remove a plugin to deploy minor code changes with

```
npm run cordova plugin remove dff-cordova-plugin-honeywell
```
and
```
npm run cordova plugin add ../dff-cordova-plugin-honeywell
```
following error message can occur:
```
(node:13188) UnhandledPromiseRejectionWarning: Unhandled promise rejection (rejection id: 1): CordovaError
```
```
(node:13188) [DEP0018] DeprecationWarning: Unhandled promise rejections are deprecated. In the future, promise rejections that are not handled will terminate the Node.js process with a non-zero exit code.
```
.

### Workaround
1. Remove plugin with:
```
npm run cordova plugin remove dff-cordova-plugin-honeywell
```
2. Discard all changes in: package.json, package-lock.json and config.xml.
```
git checkout -- package.json package-lock.json config.xml
```
3. Add plugin with:
```
npm run cordova plugin add ../dff-cordova-plugin-honeywell
```
4. Delploy application with: 
```
npm run ionic -- cordova run android
```