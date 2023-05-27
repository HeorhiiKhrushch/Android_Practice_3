# Android Practice #3. Combining all the parts of MVVM architecture.

A simple demo application, that demonstrates a usage of Repository -> ViewModel -> View data flow. 

Views (in our case - Fragments) are subscribed to Flows, provided by ViewModel, which leads to reactive updates of their states.

The only source of truth is a local DB.

You can click on the list item in order to navigate to details screen.

You can click on a star button to the right of each item, so item will be marked as isFavorite. 
The changes, that you are making that way in the details screen will also appear in a first list screen.

The network response from API is mocked, using test data and delays.

Make sure you have a proper internet connection, so preview/full-size images will be downloaded correctly.

## Technologies and libs used:

- Fragment transactions
- ViewBinding
- RecyclerView + Adapter
- Kotlin coroutines, scopes and Flows
- ViewModel, it's Factory and Delegates
- Room DB
- Retrofit