function confirmDelete(element) {
    const studentId = element.getAttribute('data-id');

    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Bạn sẽ không thể hoàn tác thao tác này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Vâng, xoá!',
        cancelButtonText: 'Huỷ'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `/students/delete-student/${studentId}`;
        }
    });

    return false;
}

function confirmDeleteCourse(element) {
    const courseId = element.getAttribute('data-id');

    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Bạn sẽ không thể hoàn tác thao tác này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Vâng, xoá!',
        cancelButtonText: 'Huỷ'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `/courses/delete-course/${courseId}`;
        }
    });

    return false;
}

function confirmDeleteEnrollment(element) {
    const enrollmentId = element.getAttribute('data-id');

    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Bạn sẽ không thể hoàn tác thao tác này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Vâng, xoá!',
        cancelButtonText: 'Huỷ'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `/enrollments/delete-enrollment/${enrollmentId}`;
        }
    });

    return false;
}