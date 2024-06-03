//
//  AppError.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import Foundation

enum AppError: Error {
    case generalError(String)
    case networkError(String)
}
