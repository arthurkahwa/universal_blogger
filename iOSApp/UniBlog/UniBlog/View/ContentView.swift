//
//  ContentView.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import SwiftUI
import CoreData

struct ContentView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @StateObject private var viewModel = ViewModel()
    @State private var selectedUser: UserPost?
    @State private var selectedPost: Post?

    var body: some View {
        NavigationSplitView {
            UserListView(selectedUser: $selectedUser)
                .navigationTitle("User List")
        } content: {
            PostListView(selectedUser: $selectedUser,
                         selectedPost: $selectedPost)
        } detail: {
            PostDetailView(selectedPost: $selectedPost)
        }
        .environmentObject(viewModel)

    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().environment(\.managedObjectContext, PersistenceController.preview.container.viewContext)
    }
}
