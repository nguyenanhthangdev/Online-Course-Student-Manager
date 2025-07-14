function redirectToStudent(selectElement) {
    const studentId = selectElement.value;
    if (studentId) {
        window.location.href = '/enrollments/filter-by-student/' + studentId;
    }
}

function redirectToCourse(selectElement) {
    const courseId = selectElement.value;
    if (courseId) {
        window.location.href = '/enrollments/filter-by-course/' + courseId;
    }
}