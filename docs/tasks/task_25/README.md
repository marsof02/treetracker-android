# Task 25 — Add instrumentation tests for critical UI flows

Branch: `chore/androidtest-ui-flows` · Issue: #1243 · PR: TBD

## Goal

`app/src/androidTest/` is empty today; UI coverage is JVM screenshot tests only.
Add instrumented UI tests (Compose testing APIs) for these critical flows:

1. Login/signup — user can create an account and sign in
2. Tree capture — user can capture a tree photo and save it
3. Sync — trees upload successfully when connectivity is available
4. Dashboard — correct tree counts and sync status display

Where live camera, GPS, or network cannot run reliably in CI/emulator, flows may use
mocks or stubs, but each flow still needs a green instrumented test before the issue closes.

## Changes

1. Create `androidTest`, add Compose instrumented dependencies, Dashboard counts/sync-status tests
2. Signup/sign-in flow (stub or bypass selfie/camera)
3. Tree capture flow (stub GPS/camera, or debug fake-trees path)
4. Sync flow (network / WorkManager doubles)


## Verification

Needs connected emulator or device online - `./gradlew connectedDebugAndroidTest`
