# Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
 
# Step 2. Add the dependency: 
 
	dependencies {
	        implementation 'com.github.batuhanerdem:custom-navtype-generator:v1.0.0'
	}

# Example Usage:

It needs the root view
```kotlin
val rootView = findViewById<ConstraintLayout>(R.id.main)
 ```
Then create the object with the root view:
```kotlin
val listener = KeyboardListener(rootView)
```

If you prefer livedata:
```kotlin
listener.asLivedata.observe(this) { result ->
            when (result) {
                is Status.Closed -> {
                    Log.d("tag", "closed")
                }

                is Status.Open -> {
                    Log.d("tag", "height: ${result.height}")
                }
            }
        }
```

If you prefer state flow:
```kotlin
lifecycleScope.launch {
            listener.asStateFlow.collect { result ->
                when (result) {
                    is Status.Closed -> {
                        Log.d("tag", "closed")
                    }

                    is Status.Open -> {
                        Log.d("tag", "height: ${result.height}")
                    }
                }
            }
        }
```

