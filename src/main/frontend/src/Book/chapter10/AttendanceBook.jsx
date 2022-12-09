//p. 300 리스트와 키

import React from 'react';

export default function AttendanceBook(props) {
    const students = [  //리스트
        { id:1, name: 'Inje'},
        { id:2, name: 'Jhon'},
        { id:3, name: 'Bill'},
        { id:4, name: 'Jeff'}
    ]
    return (
        <ul>
            {
                students.map((s) => {return <li>{s.name}</li>})
            }
        </ul>
    )
}
