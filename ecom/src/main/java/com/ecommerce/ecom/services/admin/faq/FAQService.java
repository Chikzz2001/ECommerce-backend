package com.ecommerce.ecom.services.admin.faq;

import com.ecommerce.ecom.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
