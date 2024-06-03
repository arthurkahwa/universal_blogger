//
//  Post.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import Foundation

struct Post: Codable, Identifiable, Hashable {
    let id: Int
    let userID: Int
    let title: String
    let body: String
    
    private enum CodingKeys: String, CodingKey {
        case id
        case userID = "userId"
        case title
        case body
    }
}
